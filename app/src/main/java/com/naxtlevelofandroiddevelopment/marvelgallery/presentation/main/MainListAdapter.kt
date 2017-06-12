package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.main

import android.support.v7.widget.RecyclerView
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common.BaseRecyclerViewAdapter
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common.ItemAdapter

class MainListAdapter(
        items: List<ItemAdapter<out RecyclerView.ViewHolder>>
) : BaseRecyclerViewAdapter<ItemAdapter<out RecyclerView.ViewHolder>>(
        items.toMutableList()
)