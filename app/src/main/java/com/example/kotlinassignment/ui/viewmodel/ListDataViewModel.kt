package com.example.kotlinassignment.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinassignment.data.model.Content
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.data.room_database.database.ListDataDatabase
import com.example.kotlinassignment.utils.AppConstants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ListDataViewModel(val context: Context,private var repository: ListDataRepository,var application:Application): ViewModel() {


    private var listData = MutableLiveData<List<ListDataModelAPI>>()

    internal var contentData: LiveData<List<Content>> = repository.readAllMoviesFromDB()

    init{
        val listDao = ListDataDatabase.getDatabase(application).listDataDao()
//    initializing repository and passing userDao
        repository = ListDataRepository(ListDataDatabase.getDatabase(application))
//    get all data from imageList repository in the variable readAllData
        contentData = repository.readAllMoviesFromDB()
    }
    internal val isDataAdded=MutableLiveData<Boolean>()

    val moviesDataLiveData:LiveData<List<ListDataModelAPI>>
        get() = listData
    fun getContentFromServer() {
        viewModelScope.launch {
            val response = try {
                repository.getContentFromServer()
            } catch (e: IOException) {
                Log.e(TAG,"IOException, You might not have an active internet connection", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HTTPException intercepted")
                return@launch
            }

            if(response.isSuccessful && response.body() != null) {
                Log.d(TAG, response.body().toString())
                listData.value = response.body()
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
    //    function to add list to the repository


    fun addListData(listData: List<Content>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovieDataToDB(listData)
            isDataAdded.postValue(true)
        }

    }


}