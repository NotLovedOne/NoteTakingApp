package com.example.notetakingapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetakingapp.MainViewModel
import com.example.notetakingapp.data.models.Note


@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel()
) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("userID", "")
    Column (
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val note = Note()
        val title = remember { mutableStateOf(note.title) }
        val content = remember { mutableStateOf(note.content) }



    }

}