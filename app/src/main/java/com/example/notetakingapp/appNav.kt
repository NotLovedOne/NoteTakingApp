package com.example.notetakingapp

import AddNoteScreen
import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notetakingapp.ui.screens.EditNoteScreen


@Composable
fun NoteTakingApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("add_note") {
            AddNoteScreen(navController)
        }
        composable("edit_note/{note_id}", arguments = listOf(
            navArgument("note_id") {
                type = NavType.StringType
            }
        )){
            backStackEntry ->
            EditNoteScreen(navController, backStackEntry.arguments?.getString("note_id").toString())
        }
    }
}



