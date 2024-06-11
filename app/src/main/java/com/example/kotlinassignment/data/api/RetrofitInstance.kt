package com.example.kotlinassignment.data.api

import com.example.kotlinassignment.data.di.DaggerAppComponent
import com.example.kotlinassignment.data.model.ListDataModelAPI
import retrofit2.Response

class RetrofitInstance {
    lateinit var apiInterface: ListDataInterface


    init {
     DaggerAppComponent.create().inject(this)
    }

    suspend fun readDataFromServer(): Response<List<ListDataModelAPI>> {
      return  apiInterface.getContentFromServer()
    }

}