package com.riset.antivirus.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riset.antivirus.database.ScanHistoryDao
import com.riset.antivirus.viewmodel.MainViewModel

class MainViewModelFactory(
    private val dao: ScanHistoryDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
