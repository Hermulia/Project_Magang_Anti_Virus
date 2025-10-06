package com.riset.antivirus


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.riset.antivirus.navigation.Navigation
import com.riset.antivirus.ui.theme.AntiVirusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntiVirusTheme {
                Navigation()
            }
        }
    }
}


