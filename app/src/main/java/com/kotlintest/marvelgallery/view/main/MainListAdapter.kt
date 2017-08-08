package com.kotlintest.marvelgallery.view.main

import android.support.v7.widget.RecyclerView
import com.kotlintest.marvelgallery.view.common.ItemAdapter
import com.kotlintest.marvelgallery.view.common.RecyclerListAdapter

class MainListAdapter(
        items: List<ItemAdapter<out RecyclerView.ViewHolder>>
) : RecyclerListAdapter<ItemAdapter<out RecyclerView.ViewHolder>>(
        items.toMutableList()
)