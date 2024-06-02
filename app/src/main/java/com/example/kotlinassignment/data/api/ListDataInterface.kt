package com.example.kotlinassignment.data.api

import com.example.kotlinassignment.data.model.ListDataModelAPI
import retrofit2.Response
import retrofit2.http.GET

interface ListDataInterface {

    @GET("/api/v1/movies")
    suspend fun getContentFromServer(): Response<List<ListDataModelAPI>>
}