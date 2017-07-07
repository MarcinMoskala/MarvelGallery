package com.naxtlevelofandroiddevelopment.marvelgallery.view.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemAdapter<T : RecyclerView.ViewHolder>(@LayoutRes open val layoutId: Int) {

    protected var holder: T? = null

    abstract fun onCreateViewHolder(itemView: View): T

    @Suppress("UNCHECKED_CAST")
    fun bindBaseViewHolder(holder: RecyclerView.ViewHolder) {
        this.holder = holder as T
        holder.onBindViewHolder()
    }

    abstract fun T.onBindViewHolder()
}