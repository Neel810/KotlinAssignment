package com.example.kotlinassignment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.contentplay.data.room_database.converters.ListDataConverter


data class ListDataModelAPI(
    @TypeConverters(ListDataConverter::class) @ColumnInfo(name = "page")val page: Page
)
data class Page(
    val content_items: ContentItems,
    val page_num: String,
    val page_size: String,
    val title: String,
    val total_content_items: String
)
data class ContentItems(
  @TypeConverters(ListDataConverter::class) @ColumnInfo(name = "content")  val content: ArrayList<Content>
)
@Entity(tableName = "list_data")
data class Content(
    @PrimaryKey (autoGenerate = true) val id:Int,
    val name: String,
    val poster_image: String
)