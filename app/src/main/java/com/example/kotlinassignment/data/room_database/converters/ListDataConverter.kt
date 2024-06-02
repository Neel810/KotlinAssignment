package com.example.contentplay.data.room_database.converters

import androidx.room.TypeConverter
import com.example.kotlinassignment.data.model.ListDataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListDataConverter {
    @TypeConverter
    fun fromListToString(dataList: List<String>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.toJson(dataList, type)
    }

    @TypeConverter
    fun toListFromString(dataString: String): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson<List<String>>(dataString,type)
    }


}