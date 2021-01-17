package com.android.camerax.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Image::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase : RoomDatabase() {
    abstract val imageDao: ImageDao

    companion object {
        @Volatile
        private var INSTANCE: AlbumDatabase? = null
        fun getInstance(context: Context): AlbumDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AlbumDatabase::class.java,
                        "album"
                    ).build()
                }
                return instance
            }
        }
    }
}