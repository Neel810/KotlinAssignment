package com.example.kotlinassignment.ui.main.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.data.room_database.database.ListDataDatabase
import com.example.kotlinassignment.databinding.FragmentListMainBinding
import com.example.kotlinassignment.ui.base.ListDataViewModelFactory
import com.example.kotlinassignment.ui.main.adapter.ListDataAdapter
import com.example.kotlinassignment.ui.viewmodel.ListDataViewModel
import com.example.kotlinassignment.utils.AppConstants.TAG
import com.example.kotlinassignment.utils.CommonFunction.parseJsonToModel
import com.example.kotlinassignment.utils.CommonFunction.readJsonFromAssets
import com.example.kotlinassignment.utils.LiveNetworkChecker


class ListDataViewFragment : Fragment() {

    private lateinit var listData: ListDataModelAPI
    private lateinit var listDataViewModel: ListDataViewModel
    private lateinit var binding: FragmentListMainBinding
    private val sColumnWidth = 150 // assume cell width of 120dp

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
         binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_main, container, false
        )

        val jsonString = readJsonFromAssets(requireContext(), "page_1.json")
        Log.e(TAG,"Asset JSON == "+jsonString)
         listData = parseJsonToModel(jsonString)
        Log.e(TAG,"list From JSON == "+listData.page.content_items.content.size)
        return binding.getRoot()



    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        setData()
    }

    private fun setData() {

        binding.rvContentListing.adapter = ListDataAdapter(requireActivity(), listData.page.content_items.content)
    }


}