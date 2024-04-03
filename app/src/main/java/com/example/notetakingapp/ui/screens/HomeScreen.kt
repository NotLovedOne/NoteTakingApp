package com.example.notetakingapp.ui.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetakingapp.MainViewModel
import com.example.notetakingapp.data.models.Note


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel()
) {
    val notes by viewModel.notes.collectAsState()
    val users by viewModel.users.collectAsState()
    val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Row() {
                    Button(onClick = {
                        navController.navigate("add_note")
                    }) {
                        Text(text = "Add Note")
                    }

                    Button(onClick = {
                        viewModel.clearNotes()
                    }) {
                        Text(text = "Clear notes")
                    }

                    Button(onClick = {
                        sharedPreferences.edit().clear().apply()
                        navController.navigate("login")
                    }) {
                        Text(text = "logout")
                    }
                }
            }
        }
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(notes) { note ->
                NoteItem(note = note, modifier = Modifier.padding(16.dp))
            }
        }
    }
}





@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = note._id.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Text(
            text = note.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Text(
            text = note.content,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Text(
            text = "Held by ${note.user?.name}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Light,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enrolled: ${note.categories.joinToString { it.name }}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Light,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

