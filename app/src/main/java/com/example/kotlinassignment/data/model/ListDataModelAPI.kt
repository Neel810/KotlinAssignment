package com.example.kotlinassignment.data.model

import com.google.gson.annotations.SerializedName


data class ListDataModelAPI(
    val page: Page
)
data class Page(
    val content_items: ContentItems,
    val page_num: String,
    val page_size: String,
    val title: String,
    val total_content_items: String
)
data class ContentItems(
    val content: ArrayList<Content>
)
data class Content(
    val id:Int,
    val name: String,
    val poster_image: String
)