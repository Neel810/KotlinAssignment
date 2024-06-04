package com.example.kotlinassignment.data.model

import com.google.gson.annotations.SerializedName

data class ListDataModelAPI(
//    @SerializedName("") val movieId: Int,
//    @SerializedName("") val movieName: String,
//    @SerializedName("") val movieDescription: String,
//    @SerializedName("")  val movieThumbnail: String,
//    @SerializedName("") val videoURL: String,
//    @SerializedName("") val genres: List<String>,
//    @SerializedName("") val movieCast: List<String>
//)

    val page: Page
)
data class Page(
    val content_items: ContentItems,
    val page_num: String,
    val page_size: String,
    val title: String,
    val total_items: String
)
data class ContentItems(
    val content: List<Content>
)
data class Content(
    val name: String,
    val poster_image: String
)