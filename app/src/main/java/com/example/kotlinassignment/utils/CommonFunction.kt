package com.example.kotlinassignment.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
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
                return ContextCompat.getDrawable(context.applicationContext, R.drawable.placeholder_for_missing_posters)
            }
        }
    }

    fun notNullEmpty(arrayList: ArrayList<*>?): Boolean {
        return arrayList != null && arrayList.size > 0
    }

     fun setSpannableText(
        context:Context,
        textView: AppCompatTextView,
        originalText: String,
        searchText: String
    ) {
        var index: Int = originalText.lowercase().indexOf(searchText.lowercase())
        val spannableString = SpannableString(originalText)
        while (index >= 0) {
            val endIndex: Int = index + searchText.length
            val fcs = ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.yellow
                )
            )
            spannableString.setSpan(
                fcs,
                index,
                endIndex,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )

            // Find the next occurrence of the search text
            index = originalText.lowercase().indexOf(searchText.lowercase(), endIndex)
        }
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()


    }
}

