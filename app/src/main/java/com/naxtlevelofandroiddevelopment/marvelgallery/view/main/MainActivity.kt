package com.naxtlevelofandroiddevelopment.marvelgallery.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Window
import com.naxtlevelofandroiddevelopment.marvelgallery.R
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.MainPresenter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.character.CharacterProfileActivity
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.BaseActivityWithPresenter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.addOnTextChangedListener
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.bindToSwipeRefresh
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)
    override val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager
        swipeRefreshView.setOnRefreshListener { presenter.onSearchChanged(searchView.text.toString()) }
        searchView.addOnTextChangedListener { newText -> presenter.onSearchChanged(newText) }
        presenter.onViewCreated()
    }

    override fun show(items: List<MarvelCharacter>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }

    private fun createCategoryItemAdapter(character: MarvelCharacter)
            = CategoryItemAdapter(character, { showHeroProfile(character) })

    private fun showHeroProfile(character: MarvelCharacter) {
        CharacterProfileActivity.start(this, character)
    }
}