package com.example.kotlinassignment.ui.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.ui.viewmodel.ListDataViewModel


@Suppress("UNCHECKED_CAST")
class ListDataViewModelFactory(
    private val context: Context,
    private val repository: ListDataRepository,
    private val  application: Application
)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListDataViewModel::class.java)) {
            return ListDataViewModel(context,repository,application) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}
