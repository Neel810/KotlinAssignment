package com.example.kotlinassignment.utils

import android.content.Context
import com.example.kotlinassignment.data.model.ListDataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CommonFunction{
    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
    fun parseJsonToModel(jsonString: String): List<ListDataModel> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<ListDataModel>>() {}.type)
    }
}

