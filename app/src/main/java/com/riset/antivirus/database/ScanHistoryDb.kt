package com.riset.antivirus.database

import com.riset.antivirus.model.ScanHistory
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScanHistory::class], version = 1, exportSchema = false)
abstract class ScanHistoryDatabase : RoomDatabase() {
    abstract fun scanHistoryDao(): ScanHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: ScanHistoryDatabase? = null

        fun getDatabase(context: Context): ScanHistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScanHistoryDatabase::class.java,
                    "scan_history_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
