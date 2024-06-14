package com.example.notetakingapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.data.models.Note
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId

@Suppress("UNREACHABLE_CODE")
class MainViewModel : ViewModel() {

    private val realm = MyApp.realm
    val frozenFrog = realm.query<Note>().find().first()
    val notes = realm
        .query<Note>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )


    fun clearNotes() {
        viewModelScope.launch {
            realm.write {
                notes = return@write
            }
        }
    }

    private var noteDetails: Note? by mutableStateOf(null)
        private set

    fun getNoteById(id: BsonObjectId): Note? {
        viewModelScope.launch {
            noteDetails = realm.query<Note>("id == $0", id).first().find()
        }
        return noteDetails
    }
//    fun getNoteById(id: BsonObjectId) {
//        realm.query<Note>("id == $0", id).findFirstAsync().addChangeListener { result ->
//            _noteDetails.value = result
//        }
//    }

    fun addNote(note: Note) {
        realm.writeBlocking {
            copyToRealm(note, updatePolicy = UpdatePolicy.ALL)
        }
        noteDetails = null
    }

    fun updateNote(id: BsonObjectId, title: String, content: String) {
        realm.writeBlocking {
            val note = query<Note>("id == $0", id).first().find()
            note?.title = title
            note?.content = content
        }
    }
}