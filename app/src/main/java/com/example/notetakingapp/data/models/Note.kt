package com.example.notetakingapp.data.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class Note : RealmObject {
    @PrimaryKey
    var id: ObjectId = BsonObjectId() // Unique ID for the note
    var title: String = "" // Title of the note
    var content: String = "" // Content of the note
//    var categories: RealmList<NoteCategory> = realmListOf()
}