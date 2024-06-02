package com.example.kotlinassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.contentplay.data.room_database.converters.ListDataConverter


@Entity(tableName = "list_table")
data class ListDataModel(
    @PrimaryKey val movieId: Int,
    val movieName: String,
    val movieDescription: String,
    val movieThumbnail: String,
    val videoURL: String,
    @TypeConverters(ListDataConverter::class) val genres: List<String>,
    @TypeConverters(ListDataConverter::class) val movieCast: List<String>
)

