
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notetakingapp.data.models.Note
import kotlin.random.Random

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val rnd: Random = Random
    val color = Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

    Box(
        modifier = modifier
            .border(2.dp, color = color, shape = MaterialTheme.shapes.medium)
            .clickable { onClick() }, // Add the click listener here
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = color
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}