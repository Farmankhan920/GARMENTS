package com.farman.afgarments.utils

import android.content.Context
import android.os.Environment
import com.farman.afgarments.data.SettingsManager
import com.farman.afgarments.data.models.*
import com.farman.afgarments.repository.GarmentRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import java.io.File
import java.io.FileReader
import java.io.FileWriter

data class BackupModel(
    val entries: List<Entry>,
    val expenses: List<StandaloneExpense>,
    val types: List<JacketType>,
    val workers: List<Worker>,
    val exportedAt: String
)

object JsonBackupHelper {

    suspend fun exportData(context: Context, repository: GarmentRepository): File? {
        return try {
            val backup = BackupModel(
                entries = repository.allEntries.first(),
                expenses = repository.allExpenses.first(),
                types = repository.allJacketTypes.first(),
                workers = repository.allWorkers.first(),
                exportedAt = FormatUtil.getCurrentDate()
            )

            val json = Gson().toJson(backup)
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(dir, "af-garments-backup-${System.currentTimeMillis()}.json")
            
            FileWriter(file).use { it.write(json) }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun importData(context: Context, fileUri: android.net.Uri, repository: GarmentRepository): Boolean {
        return try {
            context.contentResolver.openInputStream(fileUri)?.use { inputStream ->
                val json = inputStream.bufferedReader().use { it.readText() }
                val backup = Gson().fromJson(json, BackupModel::class.java)

                backup.entries.forEach { repository.insertEntry(it) }
                backup.expenses.forEach { repository.insertExpense(it) }
                backup.types.forEach { repository.insertJacketType(it) }
                backup.workers.forEach { repository.insertWorker(it) }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
