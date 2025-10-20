//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.riset.antivirus.model.ScanHistory
//import kotlinx.coroutines.launch
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale

//package com.riset.antivirus.screen
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
//import com.riset.antivirus.database.ScanHistoryDatabase
//import com.riset.antivirus.model.ScanHistory
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//
//class MainViewModel(application: Application) : AndroidViewModel(application) {
//    private val dao = ScanHistoryDatabase.getDatabase(application).scanHistoryDao()
//
//    val scanHistory = dao.getAllHistory()
//        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
//
//    fun addScanResult(result: String, timestamp: String) {
//        viewModelScope.launch {
//            dao.insertHistory(ScanHistory(result = result, timestamp = timestamp))
//        }
//    }
//}


//class MainViewModel(private val dao: ScanHistoryDao) : ViewModel() {
//    var historyList by mutableStateOf(listOf<ScanHistory>())
//        private set
//
//    fun loadHistory() {
//        viewModelScope.launch {
//            historyList = dao.getAllHistory()
//        }
//    }
//
//    fun addHistory(result: String, fileName: String?, scanType: String) {
//        viewModelScope.launch {
//            val time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
//            dao.insert(ScanHistory(fileName = fileName, result = result, scanType = scanType, timestamp = time))
//            loadHistory()
//        }
//    }
//
//    fun clearHistory() {
//        viewModelScope.launch {
//            dao.clearAll()
//            loadHistory()
//        }
//    }
//}


package com.riset.antivirus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riset.antivirus.database.ScanHistoryDao
import com.riset.antivirus.model.ScanHistory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(private val dao: ScanHistoryDao) : ViewModel() {

    var historyList by mutableStateOf(listOf<ScanHistory>())
        private set

    init {
        loadHistory()
    }

    fun loadHistory() {
        viewModelScope.launch {
            dao.getAllHistory().collectLatest {
                historyList = it
            }
        }
    }

     fun addHistory(result: String, fileName: String?, scanType: String) {
        viewModelScope.launch {
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
            dao.insertHistory(
                ScanHistory(
                    fileName = fileName,
                    result = result,
                    scanType = scanType,
                    timestamp = time
                )
            )
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            dao.clearAll()
        }
    }
}
