package com.example.spot.quotmotiv.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotesFav")
data class QuotesFav(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val quotes: String,
    val author :String
)
