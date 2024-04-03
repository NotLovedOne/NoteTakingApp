package com.example.notetakingapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notetakingapp.ui.screens.AddNoteScreen
import com.example.notetakingapp.ui.screens.HomeScreen
import com.example.notetakingapp.ui.screens.LoginScreen
import com.example.notetakingapp.ui.screens.RegisterScreen


@Composable
fun NoteTakingApp() {
    val navController = rememberNavController()
    val sharedPreferences = LocalContext.current.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("userID", "")
    val isLoggedIn = userId?.isNotEmpty() == true



    NavHost(navController = navController, startDestination = if (isLoggedIn) "home" else "login") {
        composable("home") {
            val sharedPreferences = LocalContext.current.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
            val userId = sharedPreferences.getString("userID", "")
            val isLoggedIn = userId?.isNotEmpty() == true
            println("isLoggedIn: $isLoggedIn + $userId" )
            if (isLoggedIn) {
                HomeScreen(navController)
            } else {
                LoginScreen(navController)
            }
        }
        composable("add_note") {
            val sharedPreferences = LocalContext.current.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
            val userId = sharedPreferences.getString("userID", "")
            val isLoggedIn = userId?.isNotEmpty() == true
            println("isLoggedIn: $isLoggedIn + $userId" )
            if (isLoggedIn) {
                AddNoteScreen(navController)
            } else {
                LoginScreen(navController)
            }
        }
        composable("login") {
            val sharedPreferences = LocalContext.current.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
            val userId = sharedPreferences.getString("userID", "")
            val isLoggedIn = userId?.isNotEmpty() == true
            println("isLoggedIn: $isLoggedIn + $userId" )
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
    }
}

