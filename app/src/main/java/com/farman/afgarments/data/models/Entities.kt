package com.farman.afgarments.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey
    val id: Long = System.currentTimeMillis(),
    val date: String,             // YYYY-MM-DD
    val jacketName: String,       // comma-separated
    val jackets: String,          // JSON array of {name, quantity, price}
    val workerName: String,
    val quantity: Int,            // total pieces
    val price: Double,            // primary jacket price
    val total: Double,            // sum of qty * price
    val expense: Double = 0.0,
    val expenseNote: String = "",
    val expenseCategory: String = "",
    val net: Double,              // total - expense
    val cuttingPcs: Int = 0,
    val cuttingRate: Double = 0.0,
    val cuttingTotal: Double = 0.0
)

@Entity(tableName = "jacket_types")
data class JacketType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Double,
    val colorHex: String = ""
)

@Entity(tableName = "workers")
data class Worker(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val role: String,
    val colorHex: String = ""
)

@Entity(tableName = "expenses")
data class StandaloneExpense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val amount: Double,
    val note: String,
    val category: String,
    val workerName: String = ""
)
