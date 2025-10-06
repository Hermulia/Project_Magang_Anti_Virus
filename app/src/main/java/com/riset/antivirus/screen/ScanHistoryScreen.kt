package com.riset.antivirus.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(navController: NavController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val history by viewModel.scanHistory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Scan History") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(history) { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Result: ${item.result}")
                        Text(text = "Time: ${item.timestamp}")
                    }
                }
            }
        }
    }
}
