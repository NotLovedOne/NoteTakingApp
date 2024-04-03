package com.example.notetakingapp.data.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class Note : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId() // Unique ID for the note
    var title: String = "" // Title of the note
    var content: String = "" // Content of the note
    var user: User? = null
    var categories: RealmList<NoteCategory> = realmListOf()
}