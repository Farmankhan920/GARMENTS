package com.farman.afgarments.repository

import com.farman.afgarments.data.dao.GarmentDao
import com.farman.afgarments.data.models.Entry
import com.farman.afgarments.data.models.JacketType
import com.farman.afgarments.data.models.StandaloneExpense
import com.farman.afgarments.data.models.Worker
import kotlinx.coroutines.flow.Flow

class GarmentRepository(private val dao: GarmentDao) {

    // --- Entries ---
    val allEntries: Flow<List<Entry>> = dao.getAllEntries()

    suspend fun insertEntry(entry: Entry) = dao.insertEntry(entry)
    suspend fun updateEntry(entry: Entry) = dao.updateEntry(entry)
    suspend fun deleteEntry(entry: Entry) = dao.deleteEntry(entry)

    fun getEntriesByWorker(workerName: String) = dao.getEntriesByWorker(workerName)
    fun getEntriesByDateRange(start: String, end: String) = dao.getEntriesByDateRange(start, end)
    suspend fun getEntryById(id: Long) = dao.getEntryById(id)

    // --- Jacket Types ---
    val allJacketTypes: Flow<List<JacketType>> = dao.getAllJacketTypes()

    suspend fun insertJacketType(jacketType: JacketType) = dao.insertJacketType(jacketType)
    suspend fun updateJacketType(jacketType: JacketType) = dao.updateJacketType(jacketType)
    suspend fun deleteJacketType(jacketType: JacketType) = dao.deleteJacketType(jacketType)

    // --- Workers ---
    val allWorkers: Flow<List<Worker>> = dao.getAllWorkers()

    suspend fun insertWorker(worker: Worker) = dao.insertWorker(worker)
    suspend fun updateWorker(worker: Worker) = dao.updateWorker(worker)
    suspend fun deleteWorker(worker: Worker) = dao.deleteWorker(worker)

    // --- Expenses ---
    val allExpenses: Flow<List<StandaloneExpense>> = dao.getAllExpenses()

    suspend fun insertExpense(expense: StandaloneExpense) = dao.insertExpense(expense)
    suspend fun updateExpense(expense: StandaloneExpense) = dao.updateExpense(expense)
    suspend fun deleteExpense(expense: StandaloneExpense) = dao.deleteExpense(expense)
}
