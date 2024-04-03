package com.example.notetakingapp.data.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class NoteCategory : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId() // Unique ID for the category
    var name: String = "" // Name of the category
    var color: Int = 0 // Color associated with the category (can be represented as an integer)
    var note: Note? = null
}