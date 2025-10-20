//package com.riset.antivirus.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import java.text.SimpleDateFormat
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScanResultScreen(result: String, navController: NavController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Scan Result") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
//        ) {
//            Text(text = result, style = MaterialTheme.typography.titleLarge)
//            Spacer(modifier = Modifier.height(24.dp))
//            Button(onClick = {
//                val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
//                viewModel.addScanResult(result, timestamp)
//                navController.popBackStack()
//            }) {
//                Text("Save to History")
//            }
//        }
//    }
//}


//package com.riset.antivirus.screen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.riset.antivirus.navigation.Screen
//
//@Composable
//fun ScanResultScreen(result: String, navController: NavController) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black)
//            .padding(24.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Scan Result",
//                color = Color.Green,
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.DarkGray)
//                    .padding(8.dp)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                ) {
//                    Text(
//                        text = result,
//                        style = MaterialTheme.typography.bodyMedium.copy(
//                            color = Color.White,
//                            fontSize = 14.sp
//                        )
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(onClick = { navController.navigate(Screen.Main.route) }) {
//                Text("Back to Main Screen")
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Button(onClick = { navController.navigate(Screen.History.route) }) {
//                Text("View History")
//            }
//        }
//    }
//}

package com.riset.antivirus.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.riset.antivirus.database.ScanHistoryDb

import com.riset.antivirus.navigation.Screen
import com.riset.antivirus.viewmodel.MainViewModel


@Composable
fun ScanResultScreen(result: String, navController: NavController) {
    val context = LocalContext.current

    // ✅ ViewModelFactory biar konsisten dan bisa akses DB
    val dao = ScanHistoryDb.getDatabase(context).scanHistoryDao()
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(dao))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Scan Result",
                color = Color.Green,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { navController.navigate(Screen.Main.route) }) {
                Text("Back to Main Screen")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.navigate(Screen.History.route) }) {
                Text("View History")
            }
        }
    }
}
