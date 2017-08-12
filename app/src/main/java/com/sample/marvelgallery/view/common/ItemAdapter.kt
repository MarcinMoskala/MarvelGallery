package com.sample.marvelgallery.view.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemAdapter<T : RecyclerView.ViewHolder>(@LayoutRes open val layoutId: Int) { // 1

    abstract fun onCreateViewHolder(itemView: View): T // 3

    @Suppress("UNCHECKED_CAST") // 1
    fun bindViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as T).onBindViewHolder() // 1
    }

    abstract fun T.onBindViewHolder() // 1, 4
}
