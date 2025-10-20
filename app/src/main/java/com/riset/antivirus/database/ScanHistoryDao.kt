
//package com.riset.antivirus.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.riset.antivirus.model.ScanHistory
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ScanHistoryDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertHistory(history: ScanHistory)
//
//    @Query("SELECT * FROM scan_history ORDER BY timestamp DESC")
//    fun getAllHistory(): Flow<List<ScanHistory>>
//}

//package com.riset.antivirus.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.riset.antivirus.model.ScanHistoryEntity
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ScanHistoryDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertScan(scan: ScanHistoryEntity)
//
//    @Query("SELECT * FROM scan_history ORDER BY dateScanned DESC")
//    fun getAllScans(): Flow<List<ScanHistoryEntity>>
//
//    @Query("DELETE FROM scan_history")
//    suspend fun clearAll()
//}

//package com.riset.antivirus.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.riset.antivirus.model.ScanHistory
//import kotlinx.coroutines.flow.Flow

//@Dao
//interface ScanHistoryDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertHistory(history: ScanHistory)
//
//    @Query("SELECT * FROM scan_history ORDER BY timestamp DESC")
//    fun getAllHistory(): Flow<List<ScanHistory>>
//
//    @Query("DELETE FROM scan_history")
//    suspend fun clearAll()
//}


//@Dao
//interface ScanHistoryDao {
//    @Insert
//    suspend fun insert(history: ScanHistory)
//
//    @Query("SELECT * FROM scan_history ORDER BY id DESC")
//    suspend fun getAllHistory(): List<ScanHistory>
//
//    @Query("DELETE FROM scan_history")
//    suspend fun clearAll()
//}
//

package com.riset.antivirus.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riset.antivirus.model.ScanHistory
import kotlinx.coroutines.flow.Flow
@Dao
interface ScanHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: ScanHistory)

    @Query("SELECT * FROM scan_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<ScanHistory>>

    @Query("DELETE FROM scan_history")
    suspend fun clearAll()
}

