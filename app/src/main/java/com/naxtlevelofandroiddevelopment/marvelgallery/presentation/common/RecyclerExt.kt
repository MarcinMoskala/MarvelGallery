@file:Suppress("UNCHECKED_CAST")

package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common

import android.support.v7.widget.RecyclerView

fun <T> RecyclerView.ViewHolder.bindView(viewId: Int) = lazy { itemView.findViewById(viewId) as T }