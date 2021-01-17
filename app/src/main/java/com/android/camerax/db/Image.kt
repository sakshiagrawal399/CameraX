package com.android.camerax.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Image (
    @PrimaryKey(autoGenerate = true)
    var albumId: Int,
    var imagePath: String,
    var tag:String,
)