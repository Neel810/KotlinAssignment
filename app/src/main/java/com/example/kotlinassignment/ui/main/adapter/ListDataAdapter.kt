package com.example.kotlinassignment.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinassignment.R
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.databinding.ListDataItemBinding
import com.example.kotlinassignment.utils.CommonFunction.setImageURL

class ListDataAdapter (
    private var contentArrayList: ArrayList<ListDataModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private lateinit var context: Context
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

    fun updatedListFromDatabase(filterList: ArrayList<ListDataModel>) {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val content = contentArrayList[position]

        //Glide library to load images into Imageviews
        Glide
            .with(binding.ivListImage)
            .load((setImageURL(context,"",binding.ivListImage)))
            .placeholder(R.drawable.ic_launcher_foreground) //Placeholder image if poster-image value is empty
            .into(binding.ivListImage)

    }


    override fun getItemCount() = contentArrayList.size

}