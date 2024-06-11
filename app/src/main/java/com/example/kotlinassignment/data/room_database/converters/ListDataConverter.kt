package com.example.contentplay.data.room_database.converters

import androidx.room.TypeConverter
import com.example.kotlinassignment.data.model.Content
import com.example.kotlinassignment.data.model.Page
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListDataConverter {
    @TypeConverter
    fun fromListToString(dataList: List<Content>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Content>>() {
        }.type
        return gson.toJson(dataList, type)
    }

    @TypeConverter
    fun toListFromString(dataString: String): List<Content>? {
        val gson = Gson()
        val type = object : TypeToken<List<Content>>() {
        }.type
        return gson.fromJson<List<Content>>(dataString,type)
    }

    @TypeConverter
    fun fromListToStringPage(dataList: Page): String? {
        val gson = Gson()
        val type = object : TypeToken<Page>() {
        }.type
        return gson.toJson(dataList, type)
    }

    @TypeConverter
    fun toListFromStringPage(dataString: String): Page? {
        val gson = Gson()
        val type = object : TypeToken<Page>() {
        }.type
        return gson.fromJson<Page>(dataString,type)
    }


}