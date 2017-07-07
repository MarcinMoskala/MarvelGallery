package com.naxtlevelofandroiddevelopment.marvelgallery.view.main

import android.support.v7.widget.RecyclerView
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.BaseRecyclerListAdapter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.ItemAdapter

class MainListAdapter(
        items: List<ItemAdapter<out RecyclerView.ViewHolder>>
) : BaseRecyclerListAdapter<ItemAdapter<out RecyclerView.ViewHolder>>(
        items.toMutableList()
)