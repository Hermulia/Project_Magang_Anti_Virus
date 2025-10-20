//package com.riset.antivirus.screen
//
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.rotate
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.riset.antivirus.navigation.Screen
//
//@Composable
//fun MainScreen(navController: NavController) {
//    val infiniteTransition = rememberInfiniteTransition(label = "scanner")
//    val rotation by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(2000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        ),
//        label = "rotation"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .size(250.dp)
//                .background(Color.DarkGray),
//            contentAlignment = Alignment.Center
//        ) {
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                rotate(rotation) {
//                    drawLine(
//                        color = Color.Green,
//                        start = center,
//                        end = center.copy(y = 0f),
//                        strokeWidth = 4f
//                    )
//                }
//                drawCircle(
//                    color = Color.Green.copy(alpha = 0.3f),
//                    radius = size.minDimension / 2
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Button(onClick = {
//                navController.navigate(Screen.Result.createRoute("Local Scan Finished"))
//            }) {
//                Text("Scan Local")
//            }
//            Button(onClick = {
//                navController.navigate(Screen.Result.createRoute("File Scan Finished"))
//            }) {
//                Text("Scan File")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            navController.navigate(Screen.History.route)
//        }) {
//            Text("View History")
//        }
//    }
//}



//package com.riset.antivirus.screen
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.rotate
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.core.content.ContextCompat
//import androidx.navigation.NavController
//import com.riset.antivirus.navigation.Screen
//
//@Composable
//fun MainScreen(navController: NavController) {
//    val context = LocalContext.current
//    val permission = Manifest.permission.READ_EXTERNAL_STORAGE
//
//    // Launcher untuk izin
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        if (isGranted) {
//            Toast.makeText(context, "Permission granted ✅", Toast.LENGTH_SHORT).show()
//            // TODO: lanjutkan scan lokal
//        } else {
//            Toast.makeText(context, "Permission denied ❌", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    // Launcher untuk pilih file
//    val pickFileLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.OpenDocument()
//    ) { uri ->
//        uri?.let {
//            Toast.makeText(context, "Picked: $uri", Toast.LENGTH_SHORT).show()
//            // TODO: analisis file yang dipilih
//        }
//    }
//
//    // Animasi radar
//    val infiniteTransition = rememberInfiniteTransition(label = "scanner")
//    val rotation by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(2000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        ),
//        label = "rotation"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .size(250.dp)
//                .background(Color.DarkGray),
//            contentAlignment = Alignment.Center
//        ) {
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                rotate(rotation) {
//                    drawLine(
//                        color = Color.Green,
//                        start = center,
//                        end = center.copy(y = 0f),
//                        strokeWidth = 4f
//                    )
//                }
//                drawCircle(
//                    color = Color.Green.copy(alpha = 0.3f),
//                    radius = size.minDimension / 2
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
//            // Tombol Scan Local (dengan izin)
//            Button(onClick = {
//                if (ContextCompat.checkSelfPermission(context, permission)
//                    == PackageManager.PERMISSION_GRANTED
//                ) {
//                    Toast.makeText(context, "Scanning local files...", Toast.LENGTH_SHORT).show()
//                    navController.navigate(Screen.Result.createRoute("Local Scan Finished"))
//                } else {
//                    permissionLauncher.launch(permission)
//                }
//            }) {
//                Text("Scan Local")
//            }
//
//            // Tombol pilih file
//            Button(onClick = {
//                pickFileLauncher.launch(arrayOf("*/*"))
//            }) {
//                Text("Pick File")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            navController.navigate(Screen.History.route)
//        }) {
//            Text("View History")
//        }
//    }
//}


//package com.riset.antivirus.screen
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.animation.core.*
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.rotate
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.riset.antivirus.navigation.Screen
//import com.riset.antivirus.viewmodel.MainViewModel
//import java.io.InputStream
//import java.security.MessageDigest
//
//@Composable
//fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
//    val context = LocalContext.current
//    val permission = Manifest.permission.READ_EXTERNAL_STORAGE
//
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        if (isGranted) {
//            val result = "Local scan completed ✅"
//            viewModel.addHistory(result, null, "Local Scan")
//            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
//            navController.navigate(Screen.Result.createRoute(result))
//        } else {
//            Toast.makeText(context, "Permission denied ❌", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    val pickFileLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.OpenDocument()
//    ) { uri ->
//        uri?.let {
//            val result = calculateFileHash(context.contentResolver.openInputStream(uri))
//            val fileName = uri.lastPathSegment ?: "Unknown"
//            viewModel.addHistory(result, fileName, "File Scan")
//            navController.navigate(Screen.Result.createRoute(result))
//        }
//    }
//
//    val infiniteTransition = rememberInfiniteTransition(label = "scanner")
//    val rotation by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(2000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        ),
//        label = "rotation"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black)
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .size(250.dp)
//                .background(Color.DarkGray),
//            contentAlignment = Alignment.Center
//        ) {
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                rotate(rotation) {
//                    drawLine(
//                        color = Color.Green,
//                        start = center,
//                        end = center.copy(y = 0f),
//                        strokeWidth = 4f
//                    )
//                }
//                drawCircle(
//                    color = Color.Green.copy(alpha = 0.3f),
//                    radius = size.minDimension / 2
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
//            Button(onClick = {
//                if (ContextCompat.checkSelfPermission(context, permission)
//                    == PackageManager.PERMISSION_GRANTED
//                ) {
//                    val result = "Local scan completed ✅"
//                    viewModel.addHistory(result, null, "Local Scan")
//                    navController.navigate(Screen.Result.createRoute(result))
//                } else {
//                    permissionLauncher.launch(permission)
//                }
//            }) {
//                Text("Scan Local")
//            }
//
//            Button(onClick = {
//                pickFileLauncher.launch(arrayOf("*/*"))
//            }) {
//                Text("Pick File")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            navController.navigate(Screen.History.route)
//        }) {
//            Text("View History")
//        }
//    }
//}
//
//fun calculateFileHash(inputStream: InputStream?): String {
//    if (inputStream == null) return "File not found"
//
//    return try {
//        val buffer = ByteArray(1024)
//        val md5 = MessageDigest.getInstance("MD5")
//        val sha256 = MessageDigest.getInstance("SHA-256")
//        var bytesRead: Int
//        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
//            md5.update(buffer, 0, bytesRead)
//            sha256.update(buffer, 0, bytesRead)
//        }
//        inputStream.close()
//
//        val md5Result = md5.digest().joinToString("") { "%02x".format(it) }
//        val shaResult = sha256.digest().joinToString("") { "%02x".format(it) }
//
//        "MD5: $md5Result\nSHA-256: $shaResult"
//    } catch (e: Exception) {
//        "Error: ${e.message}"
//    }
//}
//
//

package com.riset.antivirus.screen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.riset.antivirus.database.ScanHistoryDb
import com.riset.antivirus.navigation.Screen
import com.riset.antivirus.viewmodel.MainViewModel
import java.io.InputStream
import java.security.MessageDigest

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current

    // ✅ Tambahkan Factory agar ViewModel bisa dibuat dengan DAO dari Room
    val dao = ScanHistoryDb.getDatabase(context).scanHistoryDao()
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(dao))

    val permission = Manifest.permission.READ_EXTERNAL_STORAGE

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val result = "Local scan completed ✅"
            viewModel.addHistory(result, null, "Local Scan")
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Result.createRoute(result))
        } else {
            Toast.makeText(context, "Permission denied ❌", Toast.LENGTH_SHORT).show()
        }
    }

    val pickFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            val result = calculateFileHash(context.contentResolver.openInputStream(uri))
            val fileName = uri.lastPathSegment ?: "Unknown"
            viewModel.addHistory(result, fileName, "File Scan")
            navController.navigate(Screen.Result.createRoute(result))
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "scanner")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                rotate(rotation) {
                    drawLine(
                        color = Color.Green,
                        start = center,
                        end = center.copy(y = 0f),
                        strokeWidth = 4f
                    )
                }
                drawCircle(
                    color = Color.Green.copy(alpha = 0.3f),
                    radius = size.minDimension / 2
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                if (ContextCompat.checkSelfPermission(context, permission)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    val result = "Local scan completed ✅"
                    viewModel.addHistory(result, null, "Local Scan")
                    navController.navigate(Screen.Result.createRoute(result))
                } else {
                    permissionLauncher.launch(permission)
                }
            }) {
                Text("Scan Local")
            }

            Button(onClick = {
                pickFileLauncher.launch(arrayOf("*/*"))
            }) {
                Text("Pick File")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screen.History.route)
        }) {
            Text("View History")
        }
    }
}

fun calculateFileHash(inputStream: InputStream?): String {
    if (inputStream == null) return "File not found"

    return try {
        val buffer = ByteArray(1024)
        val md5 = MessageDigest.getInstance("MD5")
        val sha256 = MessageDigest.getInstance("SHA-256")
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            md5.update(buffer, 0, bytesRead)
            sha256.update(buffer, 0, bytesRead)
        }
        inputStream.close()

        val md5Result = md5.digest().joinToString("") { "%02x".format(it) }
        val shaResult = sha256.digest().joinToString("") { "%02x".format(it) }

        "MD5: $md5Result\nSHA-256: $shaResult"
    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}
