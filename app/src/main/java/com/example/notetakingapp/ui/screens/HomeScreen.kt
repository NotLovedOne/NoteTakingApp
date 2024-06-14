
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetakingapp.MainViewModel

@Composable
fun HomeScreen(
    navController: NavController, viewModel: MainViewModel = MainViewModel()
) {
    val notes by viewModel.notes.collectAsState()


    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                Button(onClick = { navController.navigate("add_note") }) {
                    Text(text = "Add Note")
                }

                Button(onClick = { viewModel.clearNotes() }) {
                    Text(text = "Clear notes")
                }
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.padding(6.dp),

            // number of columns
            ) {
                items(notes) { note ->
                    NoteItem(
                        onClick = { navController.navigate("edit_note/${note.id.toHexString()}",) },
                        note = note,
                        modifier = Modifier
                            .padding(6.dp),
                    )
                }
            }
        }
    }
}
