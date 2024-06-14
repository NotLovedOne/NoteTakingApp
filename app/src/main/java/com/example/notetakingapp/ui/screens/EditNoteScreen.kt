@file:Suppress("NAME_SHADOWING")

package com.example.notetakingapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.notetakingapp.MainViewModel
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditNoteScreen(
    navController: NavController,
    string: String,
    viewModel: MainViewModel = MainViewModel()
) {
    val note = viewModel.getNoteById(ObjectId(string))
    val (title, setTitle) = rememberSaveable { mutableStateOf(note!!.title) }
    val (content, setContent) = rememberSaveable { mutableStateOf(note!!.content) }

    LaunchedEffect(Unit) {
        viewModel.viewModelScope.launch {
            val note = viewModel.getNoteById(ObjectId(string))
            setTitle(note!!.title)
            setContent(note.content)
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        topBar = {
            TopAppBar(title = { Text(text = "Edit Note") })
        },
        content = {
            Column {
                TextField(value = title, onValueChange = setTitle)
                TextField(value = content, onValueChange = setContent)
            }
        },
        bottomBar = {
            Button(onClick = {
                viewModel.updateNote(ObjectId(string), title, content)
                navController.navigate("home")
            }) {
                Text(text = "Edit Note")
            }
        }
    )
}