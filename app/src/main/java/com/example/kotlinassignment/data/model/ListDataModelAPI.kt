package com.example.kotlinassignment.data.model

import com.google.gson.annotations.SerializedName

data class ListDataModelAPI(
    @SerializedName("") val movieId: Int,
    @SerializedName("") val movieName: String,
    @SerializedName("") val movieDescription: String,
    @SerializedName("")  val movieThumbnail: String,
    @SerializedName("") val videoURL: String,
    @SerializedName("") val genres: List<String>,
    @SerializedName("") val movieCast: List<String>
)