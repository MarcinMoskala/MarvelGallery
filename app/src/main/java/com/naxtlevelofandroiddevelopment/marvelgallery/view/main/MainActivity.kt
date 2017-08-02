package com.naxtlevelofandroiddevelopment.marvelgallery.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.naxtlevelofandroiddevelopment.marvelgallery.R
import com.naxtlevelofandroiddevelopment.marvelgallery.data.MarvelRepository
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
    override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        searchView.addOnTextChangedListener {
            onTextChanged { text, _, _, _ ->
                presenter.onSearchChanged(text)
            }
        }
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
            = CharacterItemAdapter(character, { showHeroProfile(character) })

    private fun showHeroProfile(character: MarvelCharacter) {
        CharacterProfileActivity.start(this, character)
    }
}