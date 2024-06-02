package com.example.kotlinassignment.ui.main.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.data.room_database.database.ListDataDatabase
import com.example.kotlinassignment.ui.base.ListDataViewModelFactory
import com.example.kotlinassignment.ui.viewmodel.ListDataViewModel
import com.example.kotlinassignment.utils.LiveNetworkChecker

class ListDataViewFragment : Fragment() {

    private lateinit var listDataViewModel: ListDataViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context?.let { context ->
            val repository = ListDataRepository(ListDataDatabase.getDatabase(context))
            val factory = ListDataViewModelFactory(repository)
            listDataViewModel = ViewModelProvider(this, factory)[ListDataViewModel::class.java]
        }
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LiveNetworkChecker.init(requireActivity().application)
        if(!LiveNetworkChecker.hasObservers()) {
            LiveNetworkChecker.observe(viewLifecycleOwner) { isAvailable ->
                if(isAvailable) {
                    listDataViewModel.getContentFromServer()
                }
            }
        }
        observe()
    }

    private fun observe() {
        listDataViewModel.moviesDataLiveData.observe(viewLifecycleOwner) {
            //Log.d(TAG, it.toString())
        }
    }
}