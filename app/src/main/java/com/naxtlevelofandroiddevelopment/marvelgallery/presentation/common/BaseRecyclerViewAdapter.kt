package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

open class BaseRecyclerViewAdapter<in T : ItemAdapter<out RecyclerView.ViewHolder>>(
        private val items: MutableList<T>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override final fun getItemCount() = items.size

    override final fun getItemViewType(position: Int) = items[position].layoutId

    override final fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return items.first { it.layoutId == layoutId }.onCreateViewHolder(itemView)
    }

    override final fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].onBindBaseViewHolder(holder)
    }
}