package com.example.kotlinassignment.ui.main.view.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.Content
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.data.repository.ListDataRepository
import com.example.kotlinassignment.data.room_database.database.ListDataDatabase
import com.example.kotlinassignment.databinding.FragmentListMainBinding
import com.example.kotlinassignment.ui.base.ListDataViewModelFactory
import com.example.kotlinassignment.ui.main.adapter.ListDataAdapter
import com.example.kotlinassignment.ui.main.view.activities.MainActivity
import com.example.kotlinassignment.ui.viewmodel.ListDataViewModel
import com.example.kotlinassignment.utils.AppConstants.TAG
import com.example.kotlinassignment.utils.CommonFunction.calculateSize
import com.example.kotlinassignment.utils.CommonFunction.notNullEmpty
import com.example.kotlinassignment.utils.CommonFunction.parseJsonToModel
import com.example.kotlinassignment.utils.CommonFunction.readJsonFromAssets
import com.example.kotlinassignment.utils.RecyclerViewScrollListener


class ListDataViewFragment : Fragment() {


    private  var pixel: Int=0
    private lateinit var listConentData: ArrayList<Content>
    private lateinit var listData: ListDataModelAPI

    private lateinit var listDataViewModel: ListDataViewModel
    private lateinit var binding: FragmentListMainBinding

    //
    private lateinit var listDataAdapter: ListDataAdapter
    private var isClicked: Boolean = false

    //
    private lateinit var scrollListener: RecyclerViewScrollListener
    private var mTotalCount = 0
    private var mPage = 1
    private var isPaginate = false
    private val sColumnWidth = 90

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
            val factory = ListDataViewModelFactory(context, repository)
            listDataViewModel = ViewModelProvider(this, factory)[ListDataViewModel::class.java]
        }
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_main, container, false
        )

        /*Setting List Data on page 1*/
        setListData("page_1.json")

        setViewTreeObserveData()
        return binding.getRoot()

    }

    /*Set List Data from asset json*/
    private fun setListData(strJson: String) {
        val jsonString = readJsonFromAssets(requireContext(), strJson)
        Log.e(TAG, "Asset JSON == " + jsonString)
        listData = parseJsonToModel(jsonString)
        //
        setScrollListnerView()
        setListAdapter(listData.page.content_items.content)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        hideShowSearchView()
        setSearchFilterList()
        getDatabaseObserve()
        //
        binding.ivBack.setOnClickListener{ view ->
            (activity as MainActivity?)?.onBackPressed()
        }
    }

    private fun getDatabaseObserve() {
        listDataViewModel.isDataAdded.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,
                context?.let { it1 -> ContextCompat.getString(it1,R.string.data_added_successfully) }, Toast.LENGTH_SHORT).show()


            Log.e(TAG, "List DB data size== " + listDataViewModel.getListData())
        })
    }


    private fun hideShowSearchView() {

        binding.ivSearch.setOnClickListener { view ->
            // Do some work here
            if (!isClicked) {

                isClicked = true
                setSearchBarHideShowView(GONE, VISIBLE,
                    context?.let {
                        ContextCompat.getDrawable(
                            it.applicationContext,
                            R.drawable.search_cancel
                        )
                    })
            } else {
                binding.etSearch.setText("")
                isClicked = false
                setSearchBarHideShowView(VISIBLE, GONE, context?.let {
                    ContextCompat.getDrawable(
                        it.applicationContext,
                        R.drawable.search
                    )
                })

            }

        }
    }

    /*Show Hide View for SearchView and HEader*/
    private fun setSearchBarHideShowView(isTvFragmentHeader: Int, isEtSearch: Int, rightIcon: Drawable?) {
        //Show Search View
        binding.tvFragmentHeader.visibility = isTvFragmentHeader
        binding.etSearch.visibility = isEtSearch
        //
        binding.ivSearch.setImageDrawable(rightIcon)
        binding.ivBack.visibility = isTvFragmentHeader
    }

    /*List set Data to adapter*/
    private fun setListAdapter(content: ArrayList<Content>) {

        // addressList = resultList
        mTotalCount = listData.page.total_content_items.toInt()
        Log.e(TAG, "Page No: ${mPage} total count == " + mTotalCount)

        /*Set Data*/

        if (mPage > 1) {
            if (::listDataAdapter.isInitialized) {
                listConentData.addAll(content)
                listDataAdapter.updateArrayList(listConentData, "")

                //store updated data to DB
                listDataViewModel.addListData(listConentData)
                Log.e(TAG, "Page No: ${mPage} updated list From JSON == " + listConentData.size)
            }
        } else {
            /*Set List Data for Page 1 */
            listConentData = ArrayList()
            //
            listConentData = listData.page.content_items.content

            //store new data to DB
            listDataViewModel.addListData(listConentData)
            Log.e(TAG, "Page No: ${mPage} list From JSON == " + listConentData.size)
            //
            if (notNullEmpty(listConentData)) {
                listDataAdapter = ListDataAdapter(requireActivity(), listConentData)
                binding.rvContentListing.adapter = listDataAdapter
            }

        }
        /*Enable Disable ScrollListner*/
        if (listConentData.size == mTotalCount) {
            isPaginate = false
            scrollListener.disableScrollListener()
        } else {
            isPaginate = true
            scrollListener.enableScrollListener()
        }

    }

    /*Search from list data*/
    private fun setSearchFilterList() {

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                //if (editable.toString().length > 2) {

                    filter(editable.toString())
                //}
            }
        })
    }


    //filter text list data
    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filterdNames: ArrayList<Content> = ArrayList()

        //looping through existing elements
        if (::listConentData.isInitialized && listConentData.size > 0) {
            for (s in listConentData) {
                //if the existing elements contains the search input
                if (s.name.lowercase().contains(text.lowercase())
                    || s.name.uppercase().contains(text.uppercase())
                ) {
                    //adding the element to filtered list
                    filterdNames.add(s)
                }
            }

            //calling a method of the adapter class and passing the filtered list
            listDataAdapter.updateArrayList(filterdNames, text)
        }
    }

    private fun setScrollListnerView() {
        /*Pagination Scroll Listner*/
        scrollListener = object : RecyclerViewScrollListener() {
            override fun onEndOfScrollReached(recyclerView: RecyclerView?) {
                if (isPaginate) {
                    //  addressList.add(null)
                    isPaginate = false
                    mPage++
                    if (mPage == 2) {
                        setListData("page_2.json")
                    } else if (mPage == 3) {
                        setListData("page_3.json")
                    }

                }
            }

        }
        binding.rvContentListing.addOnScrollListener(scrollListener)
    }

    /*Set Span count Dynamically*/
    private fun setViewTreeObserveData() {
        val viewTreeObserver: ViewTreeObserver = binding.rvContentListing.getViewTreeObserver()
        viewTreeObserver.addOnGlobalLayoutListener { calculateSize(requireActivity(),binding.rvContentListing) }
    }






}