package com.example.spot.quotmotiv.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "writeQuotes")
data class WriteQuotes(
    @PrimaryKey(autoGenerate = true)
    val idWrite : Int,
    val quotesWrite: String,
    val authorWrite :String
)
