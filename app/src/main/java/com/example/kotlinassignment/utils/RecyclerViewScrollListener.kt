package com.example.kotlinassignment.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.Volatile

abstract class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {
    @Volatile
    private var mEnabled = true

    private var mPreLoadCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (mEnabled) {
            val manager = recyclerView.layoutManager

            require(manager is LinearLayoutManager) { "Expected recyclerview to have linear layout manager" }
            val mLayoutManager = manager

            val visibleItemCount = mLayoutManager.childCount
            val totalItemCount = mLayoutManager.itemCount
            val firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
            val lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()

            if (firstVisibleItem + visibleItemCount >= totalItemCount - mPreLoadCount) {
                onEndOfScrollReached(recyclerView)
            }
        }
    }

    /**
     * Called when end of scroll is reached.
     *
     * @param recyclerView - related recycler view.
     */
    abstract fun onEndOfScrollReached(recyclerView: RecyclerView?)

    fun disableScrollListener() {
        mEnabled = false
    }

    fun enableScrollListener() {
        mEnabled = true
    }

    fun setPreLoadCount(mPreLoadCount: Int) {
        this.mPreLoadCount = mPreLoadCount
    }
}
