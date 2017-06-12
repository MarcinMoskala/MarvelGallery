package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemAdapter<T : RecyclerView.ViewHolder>(@LayoutRes open val layoutId: Int) {

    var holder: T? = null

    abstract fun onCreateViewHolder(itemView: View): T

    @Suppress("UNCHECKED_CAST")
    fun onBindBaseViewHolder(holder: RecyclerView.ViewHolder) {
        this.holder = holder as T
        holder.onBindViewHolder()
    }

    abstract fun T.onBindViewHolder()
}