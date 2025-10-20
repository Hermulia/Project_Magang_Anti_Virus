//package com.riset.antivirus.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScanHistoryScreen(navController: NavController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
//    val history by viewModel.scanHistory.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Scan History") })
//        }
//    ) { padding ->
//        LazyColumn(
//            modifier = Modifier
//                .padding(padding)
//                .fillMaxSize()
//        ) {
//            items(history) { item ->
//                Card(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(text = "Result: ${item.result}")
//                        Text(text = "Time: ${item.timestamp}")
//                    }
//                }
//            }
//        }
//    }
//}


//package com.riset.antivirus.screen
//
//import MainViewModel
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//
//private val Int.scanType: String
//private val Int.fileName: Any
//private val Int.result: String
//private val Int.timestamp: String
//
//@Composable
//fun ScanHistoryScreen(viewModel: MainViewModel = viewModel()) {
//    val historyList by viewModel.history.collectAsState()
//
//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        Text("Scan History", modifier = Modifier.padding(bottom = 8.dp))
//        LazyColumn(modifier = Modifier.weight(1f)) {
//            items(historyList) { item ->
//                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
//                    Column(modifier = Modifier.padding(8.dp)) {
//                        Text("Type: ${item.scanType}")
//                        item.fileName?.let { Text("File: $it") }
//                        Text("Result: ${item.result}")
//                        Text("Time: ${item.timestamp}")
//                    }
//                }
//            }
//        }
//        Button(onClick = { viewModel.clearAll() }, modifier = Modifier.fillMaxWidth()) {
//            Text("Clear History")
//        }
//    }
//}


//package com.riset.antivirus.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.riset.antivirus.model.ScanHistory
//import com.riset.antivirus.viewmodel.MainViewModel
//
//
//@Composable
//fun ScanHistoryScreen(
//    navController: NavController,
//    viewModel: MainViewModel = viewModel()
//) {
//    val historyList = viewModel.historyList
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text("Scan History", modifier = Modifier.padding(bottom = 8.dp))
//
//        LazyColumn(modifier = Modifier.weight(1f)) {
//            items(historyList) { item ->
//                ScanHistoryItem(item)
//            }
//        }
//
//        Button(
//            onClick = { viewModel.clearHistory() },
//            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
//        ) {
//            Text("Clear History")
//        }
//    }
//}
//
//@Composable
//fun ScanHistoryItem(item: ScanHistory) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Column(modifier = Modifier.padding(8.dp)) {
//            Text("Type: ${item.scanType}")
//            item.fileName?.let { Text("File: $it") }
//            Text("Result: ${item.result}")
//            Text("Time: ${item.timestamp}")
//        }
//    }
//}


package com.riset.antivirus.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.riset.antivirus.database.ScanHistoryDb
import com.riset.antivirus.model.ScanHistory
import com.riset.antivirus.viewmodel.MainViewModel

@Composable
fun ScanHistoryScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val dao = remember { ScanHistoryDb.getDatabase(context).scanHistoryDao() }
    val factory = remember { MainViewModelFactory(dao) }
    val viewModel: MainViewModel = viewModel(factory = factory)

    val historyList by remember { derivedStateOf { viewModel.historyList } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Scan History", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(historyList) { item ->
                ScanHistoryItem(item)
            }
        }

        Button(
            onClick = { viewModel.clearHistory() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Clear History")
        }
    }
}

@Composable
fun ScanHistoryItem(item: ScanHistory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Type: ${item.scanType}")
            item.fileName?.let { Text("File: $it") }
            Text("Result: ${item.result}")
            Text("Time: ${item.timestamp}")
        }
    }
}
