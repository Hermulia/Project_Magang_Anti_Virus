//package com.riset.antivirus.database
//
//import com.riset.antivirus.model.ScanHistory
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [ScanHistory::class], version = 1, exportSchema = false)
//abstract class ScanHistoryDatabase : RoomDatabase() {
//    abstract fun scanHistoryDao(): ScanHistoryDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ScanHistoryDatabase? = null
//
//        fun getDatabase(context: Context): ScanHistoryDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ScanHistoryDatabase::class.java,
//                    "scan_history_db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}

//package com.riset.antivirus.database
//
//import ScanHistoryDao
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.riset.antivirus.model.ScanHistory
//
//@Database(entities = [ScanHistory::class], version = 1, exportSchema = false)
//abstract class ScanHistoryDatabase : RoomDatabase() {
//    abstract fun scanHistoryDao(): ScanHistoryDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ScanHistoryDatabase? = null
//
//        fun getDatabase(context: Context): ScanHistoryDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ScanHistoryDatabase::class.java,
//                    "scan_history_db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
//

package com.riset.antivirus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.riset.antivirus.model.ScanHistory

@Database(entities = [ScanHistory::class], version = 2, exportSchema = false)
abstract class ScanHistoryDb : RoomDatabase() {
    abstract fun scanHistoryDao(): ScanHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: ScanHistoryDb? = null

        fun getDatabase(context: Context): ScanHistoryDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScanHistoryDb::class.java,
                    "scan_history_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
