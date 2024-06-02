package com.example.kotlinassignment.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.utils.AppConstants.TAG
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ListDataViewModel(private var repository: ListDataRepository): ViewModel() {

    private var listData = MutableLiveData<List<ListDataModelAPI>>()
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
}