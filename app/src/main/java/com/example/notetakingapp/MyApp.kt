package com.example.notetakingapp

import android.app.Application
import com.example.notetakingapp.data.models.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApp: Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() { 
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
//                    User::class,
                    Note::class,
//                    NoteCategory::class
                )
            )
        )
    }
}

