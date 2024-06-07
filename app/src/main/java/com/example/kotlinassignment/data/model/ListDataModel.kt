package com.example.kotlinassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.contentplay.data.room_database.converters.ListDataConverter


@Entity(tableName = "list_data")
data class ListDataModel(


    @PrimaryKey val id:Int,
    val name: String,
    val poster_image: String


)






