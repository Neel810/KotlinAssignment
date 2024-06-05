package com.example.kotlinassignment.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.Content
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.databinding.ListDataItemBinding
import com.example.kotlinassignment.utils.CommonFunction.setImageURL
import com.example.kotlinassignment.utils.CommonFunction.setListRowImage

class ListDataAdapter(
    private var context: Context?,
    private var contentArrayList: List<Content>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private lateinit var binding: ListDataItemBinding
    private var query: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.list_data_item,
            parent,
            false
        )


        return MyViewHolder(binding.root)
    }

    fun updatedSearchList(filterList: ArrayList<Content>) {
        contentArrayList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val content = contentArrayList[position]

        binding.tvTitle.text = content.name
        //Glide library to load images into Imageviews
        Glide
            .with(binding.ivListImage)
            .load((context?.let { setListRowImage(it, content.poster_image) }))
            .placeholder(R.drawable.placeholder_for_missing_posters)
            .into(binding.ivListImage)

    }


    override fun getItemCount() = contentArrayList.size

}