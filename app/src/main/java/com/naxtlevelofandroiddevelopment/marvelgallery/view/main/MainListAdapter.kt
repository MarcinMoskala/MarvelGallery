package com.naxtlevelofandroiddevelopment.marvelgallery.view.main

import android.support.v7.widget.RecyclerView
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.ItemAdapter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.RecyclerListAdapter

class MainListAdapter(
        items: List<ItemAdapter<out RecyclerView.ViewHolder>>
) : RecyclerListAdapter<ItemAdapter<out RecyclerView.ViewHolder>>(
        items.toMutableList()
)