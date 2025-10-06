package com.riset.antivirus.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.riset.antivirus.database.ScanHistoryDatabase
import com.riset.antivirus.model.ScanHistory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = ScanHistoryDatabase.getDatabase(application).scanHistoryDao()

    val scanHistory = dao.getAllHistory()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addScanResult(result: String, timestamp: String) {
        viewModelScope.launch {
            dao.insertHistory(ScanHistory(result = result, timestamp = timestamp))
        }
    }
}
