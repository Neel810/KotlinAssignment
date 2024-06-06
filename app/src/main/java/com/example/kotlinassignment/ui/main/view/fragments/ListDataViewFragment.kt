package com.example.kotlinassignment.ui.main.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuItemCompat
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.Content
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


class ListDataViewFragment : Fragment() {



    private lateinit var listData: ListDataModelAPI
    private lateinit var listDataViewModel: ListDataViewModel
    private lateinit var binding: FragmentListMainBinding
    private lateinit var contentList: ArrayList<Content>
    private lateinit var listDataAdapter: ListDataAdapter
    private var isClicked: Boolean=false
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


        setListData("page_1.json")
        return binding.getRoot()


    }

    /*Set List Data from asset json*/
    private fun setListData(strJson: String) {
        val jsonString = readJsonFromAssets(requireContext(), strJson)
        Log.e(TAG, "Asset JSON == " + jsonString)
        listData = parseJsonToModel(jsonString)
        Log.e(TAG, "list From JSON == " + listData.page.content_items.content.size)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        setData()
    }

    private fun setData() {
       hideShowSearchView()
        //
        setListAdapter()
        //
        setSearchFilterList()

    }

    private fun hideShowSearchView() {

        binding.ivSearch.setOnClickListener { view ->
            // Do some work here
            Log.e(TAG, "Clicked")



            if (!isClicked) {
                //Show Search View
                binding.tvFragmentHeader.visibility = GONE
                binding.etSearch.visibility = VISIBLE
                //
                binding.ivSearch.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it.applicationContext,
                            R.drawable.search_cancel
                        )
                    })
                binding.ivBack.visibility = GONE
                isClicked = true
            } else {
                binding.etSearch.setText("")
                binding.tvFragmentHeader.visibility = VISIBLE
                binding.etSearch.visibility = GONE
                //
                binding.ivSearch.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it.applicationContext,
                            R.drawable.search
                        )
                    })

                binding.ivBack.visibility = VISIBLE
                isClicked=false
            }

        }
    }


    /*List set Data to adapter*/
    private fun setListAdapter() {
        contentList = listData.page.content_items.content
        listDataAdapter = ListDataAdapter(requireActivity(), contentList)
        binding.rvContentListing.adapter = listDataAdapter
    }

    /*Search from list data*/
    private fun setSearchFilterList() {

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                if (editable.toString().length > 0) {
                    binding.ivSearch.setImageDrawable(
                        context?.let {
                            ContextCompat.getDrawable(
                                it.applicationContext,
                                R.drawable.search_cancel
                            )
                        })
                }
                if (editable.toString().length == 0) {
                    binding.ivSearch.setImageDrawable(
                        context?.let {
                            ContextCompat.getDrawable(
                                it.applicationContext,
                                R.drawable.search
                            )
                        })
                }
                filter(editable.toString())
            }
        })
    }


    //filter text list data
    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filterdNames: ArrayList<Content> = ArrayList()

        //looping through existing elements
        if (::contentList.isInitialized && contentList.size > 0) {
            for (s in contentList) {
                //if the existing elements contains the search input
                if (s.name.lowercase().contains(text.lowercase())
                    || s.name.uppercase().contains(text.uppercase())
                ) {
                    //adding the element to filtered list
                    filterdNames.add(s)
                }
            }

            //calling a method of the adapter class and passing the filtered list
            listDataAdapter.updatedSearchList(filterdNames)
        }
    }


}