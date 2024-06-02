package com.example.kotlinassignment.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.ui.viewmodel.ListDataViewModel


@Suppress("UNCHECKED_CAST")
class ListDataViewModelFactory(
    private val repository: ListDataRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListDataModel::class.java)) {
            return ListDataViewModel(repository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}
