package com.example.spot.quotmotiv.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuotesFav::class,WriteQuotes::class], version = 1)
abstract class QuotesFavDataBase : RoomDatabase() {
    abstract fun quotesFavDAO() : QuotesFavDAO
}