package com.example.kotlinassignment.data.api

import com.example.kotlinassignment.data.di.DaggerAppComponent
import com.example.kotlinassignment.data.model.ListDataModelAPI
import retrofit2.Response

/*
* Structure of API URL -
*     Example - https://apipreprod.sonyliv.com/AGL/2.7/R/ENG/ANDROID_TAB/IN/KA/FEATURE/CONFIG?
*     Base URL - https://apipreprod.sonyliv.com
*     End Points - /AGL/2.7/R/ENG/ANDROID_TAB/IN/KA/FEATURE/CONFIG?
* */
class RetrofitInstance {


    /*Structure of API -
    * Headers
    * Request -> Body
    * Response -> Body
    *Tests
    * Code -> 200 (Success), 400, 404, 405, 402, 401, 301, 300,.............
    * */

    lateinit var apiInterface: ListDataInterface


    init {
     DaggerAppComponent.create().inject(this)
    }

    suspend fun readDataFromServer(): Response<List<ListDataModelAPI>> {
      return  apiInterface.getContentFromServer()
    }

}