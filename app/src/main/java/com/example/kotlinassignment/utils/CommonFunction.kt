package com.example.kotlinassignment.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.utils.AppConstants.POSTER_1
import com.example.kotlinassignment.utils.AppConstants.POSTER_2
import com.example.kotlinassignment.utils.AppConstants.POSTER_3
import com.example.kotlinassignment.utils.AppConstants.POSTER_4
import com.example.kotlinassignment.utils.AppConstants.POSTER_5
import com.example.kotlinassignment.utils.AppConstants.POSTER_6
import com.example.kotlinassignment.utils.AppConstants.POSTER_7
import com.example.kotlinassignment.utils.AppConstants.POSTER_8
import com.example.kotlinassignment.utils.AppConstants.POSTER_9
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
    fun setListRowImage(context: Context,image:String):Drawable?{
        when (image) {
            POSTER_1 -> return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_1 )
            POSTER_2 -> return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_2 )
            POSTER_3 ->return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_3)
            POSTER_4 -> return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_4)
            POSTER_5 ->return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_5)
            POSTER_6 ->return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_6)
            POSTER_7 ->return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_7)
            POSTER_8-> return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_8)
            POSTER_9 ->return ContextCompat.getDrawable(context.applicationContext, R.drawable.poster_9)

            else -> {
                return ContextCompat.getDrawable(context.applicationContext, R.drawable.ic_launcher_background)
            }
        }
    }
}

