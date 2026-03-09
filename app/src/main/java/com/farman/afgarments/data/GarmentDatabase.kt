package com.farman.afgarments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farman.afgarments.data.dao.GarmentDao
import com.farman.afgarments.data.models.*

@Database(
    entities = [Entry::class, JacketType::class, Worker::class, StandaloneExpense::class],
    version = 1,
    exportSchema = false
)
abstract class GarmentDatabase : RoomDatabase() {

    abstract fun garmentDao(): GarmentDao

    companion object {
        @Volatile
        private var INSTANCE: GarmentDatabase? = null

        fun getDatabase(context: Context): GarmentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GarmentDatabase::class.java,
                    "afgarments_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
