package com.example.kotlinassignment.data.repository

import androidx.lifecycle.LiveData
import com.example.kotlinassignment.data.api.RetrofitInstance
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.data.room_database.database.ListDataDatabase

import retrofit2.Response
import javax.inject.Inject

class ListDataRepository (database: ListDataDatabase) {

    private val listDataDAO = database.listDataDao()
    @Inject
   lateinit var retrofitInstance: RetrofitInstance

    suspend fun addMovieDataToDB(movies: List<ListDataModel>) {
        listDataDAO.addListDataToDB(movies)
    }

    fun readAllMoviesFromDB(): LiveData<List<ListDataModel>> {
        return listDataDAO.readAllListFromDB()
    }

//    fun readAllMoviesFromDBWithSearch(query: String): LiveData<List<ListDataModel>> {
//        //return listDataDAO.readAllListFromDBWithSearch(query)
//    }

    suspend fun deleteMoviesDataFromDB() {
        listDataDAO.deleteListDataFromDB()
    }

    suspend fun getContentFromServer(): Response<List<ListDataModelAPI>> {
        return retrofitInstance.readDataFromServer()
    }

}