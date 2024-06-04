package com.example.kotlinassignment.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CommonFunction{
    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
    fun parseJsonToModel(jsonString: String): ListDataModelAPI {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<ListDataModelAPI>() {}.type)
    }

    fun setImageURL(context: Context,image:String,imageView: AppCompatImageView) {


        Glide.with(context)
            .load(image)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}

