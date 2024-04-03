package com.example.notetakingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notetakingapp.ui.screens.AddNoteScreen
import com.example.notetakingapp.ui.screens.HomeScreen
import com.example.notetakingapp.ui.screens.LoginScreen
import com.example.notetakingapp.ui.screens.RegisterScreen
import com.example.notetakingapp.ui.theme.NoteTakingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteTakingAppTheme {
                NoteTakingApp()
            }
        }
    }
}

