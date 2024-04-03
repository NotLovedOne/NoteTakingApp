package com.example.notetakingapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.data.models.Note
import com.example.notetakingapp.data.models.User
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE")
class MainViewModel: ViewModel() {

    private val realm = MyApp.realm

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

    val users = realm
        .query<User>()
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

    var noteDetails: Note? by mutableStateOf(null)
        private set


    fun addNote(note: Note) {
        viewModelScope.launch {
            realm.write {
                copyToRealm(note, updatePolicy = UpdatePolicy.ALL)
            }
            noteDetails = null
        }
    }

    fun userDetails(name: String): User? {
        return realm.query<User>("name == $0", name).first().find()
    }

    fun getNotes(user: User): List<Note> {
        return realm.query<Note>("user == $", user).find()
    }



    fun findUser(id: String) :  User?{
        return realm.query<User>("_id == oid($id)").first().find()
    }


    fun addUser(name: String, password: String){
        viewModelScope.launch {
            realm.write {
                val user = User().apply {
                    this.name = name
                    this.password = password
                    this.notes.addAll(notes)
                }
                copyToRealm(user, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}