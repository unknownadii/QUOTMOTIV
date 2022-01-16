package com.example.spot.quotmotiv.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuotesFavDAO {

    // For favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes (quotesFav : QuotesFav)

    @Delete
    suspend fun deleteQuotes(quotesFav: QuotesFav)

    @Query("SELECT * FROM quotesFav")
    fun getAllQuotes():LiveData<List<QuotesFav>>

    // write quotes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWriteQuotes (quotesWrite : WriteQuotes)

    @Delete
    suspend fun deleteWriteQuotes(quotesWrite: WriteQuotes)

    @Query("SELECT * FROM writeQuotes")
    fun getAllWriteQuotes():LiveData<List<WriteQuotes>>
}