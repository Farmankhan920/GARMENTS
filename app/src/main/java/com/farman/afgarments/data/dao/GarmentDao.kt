package com.farman.afgarments.data.dao

import androidx.room.*
import com.farman.afgarments.data.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GarmentDao {
    // --- Entry ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: Entry)

    @Update
    suspend fun updateEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Query("SELECT * FROM entries ORDER BY date DESC, id DESC")
    fun getAllEntries(): Flow<List<Entry>>

    @Query("SELECT * FROM entries WHERE workerName = :workerName ORDER BY date DESC, id DESC")
    fun getEntriesByWorker(workerName: String): Flow<List<Entry>>

    @Query("SELECT * FROM entries WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, id DESC")
    fun getEntriesByDateRange(startDate: String, endDate: String): Flow<List<Entry>>

    @Query("SELECT * FROM entries WHERE id = :entryId")
    suspend fun getEntryById(entryId: Long): Entry?

    // --- JacketType ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJacketType(jacketType: JacketType)

    @Update
    suspend fun updateJacketType(jacketType: JacketType)

    @Delete
    suspend fun deleteJacketType(jacketType: JacketType)

    @Query("SELECT * FROM jacket_types ORDER BY name ASC")
    fun getAllJacketTypes(): Flow<List<JacketType>>

    // --- Worker ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: Worker)

    @Update
    suspend fun updateWorker(worker: Worker)

    @Delete
    suspend fun deleteWorker(worker: Worker)

    @Query("SELECT * FROM workers ORDER BY name ASC")
    fun getAllWorkers(): Flow<List<Worker>>

    // --- StandaloneExpense ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: StandaloneExpense)

    @Update
    suspend fun updateExpense(expense: StandaloneExpense)

    @Delete
    suspend fun deleteExpense(expense: StandaloneExpense)

    @Query("SELECT * FROM expenses ORDER BY date DESC, id DESC")
    fun getAllExpenses(): Flow<List<StandaloneExpense>>
}
