
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetakingapp.MainViewModel
import com.example.notetakingapp.data.models.Note

@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel(),
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val note = Note()
        val title = remember { mutableStateOf(note.title) }
        val content = remember { mutableStateOf(note.content) }

        TextField(value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Title") })

        TextField(value = content.value,
            onValueChange = { content.value = it },
            label = { Text("Content") })

        Button(onClick = {
            navController.navigate("home")
            note.title = title.value
            note.content = content.value
            viewModel.addNote(
                note = note
            )
        }) {
            Text("Save")
        }
    }
}
