package com.android.camerax.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ImageDao {
    @Insert
    suspend fun saveImages(image: Image):Long

    @Query("SELECT * FROM Image WHERE tag=:tag")
    fun getImages(tag: String): LiveData<List<Image>>

    @Query("SELECT DISTINCT tag From Image")
    fun getAlbums(): LiveData<List<String>>
}