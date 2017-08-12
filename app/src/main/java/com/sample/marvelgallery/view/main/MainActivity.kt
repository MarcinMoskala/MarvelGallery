package com.sample.marvelgallery.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.sample.marvelgallery.R
import com.sample.marvelgallery.data.MarvelRepository
import com.sample.marvelgallery.model.MarvelCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val characters = listOf( // 1
            MarvelCharacter(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            MarvelCharacter(name = "Abomination (Emil Blonsky)", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 2
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 3
        val categoryItemAdapters = characters.map(::CharacterItemAdapter) // 4
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }
}
