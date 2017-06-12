package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.naxtlevelofandroiddevelopment.marvelgallery.R
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common.ItemAdapter
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common.bindView
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common.loadImage

class CategoryItemAdapter(
        val character: MarvelCharacter,
        val clicked: (MarvelCharacter) -> Unit
) : ItemAdapter<CategoryItemAdapter.ViewHolder>(R.layout.item_main) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        itemView.setOnClickListener { clicked(character) }
        textView.text = character.name
        imageView.loadImage(character.imageUrl)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView by bindView<TextView>(R.id.textView)
        val imageView by bindView<ImageView>(R.id.imageView)
    }
}
