package com.kotlintest.marvelgallery.presenter

import com.kotlintest.marvelgallery.data.MarvelRepository
import com.kotlintest.marvelgallery.data.network.applySchedulers
import com.kotlintest.marvelgallery.data.network.plusAssign
import com.kotlintest.marvelgallery.data.network.smartSubscribe
import com.kotlintest.marvelgallery.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) : BasePresenter() {

    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    fun onSearchChanged(newText: String) {
        loadCharacters(newText)
    }

    private fun loadCharacters(search: String? = null) {
        val qualifiedSearchQuery = if (search.isNullOrBlank()) null else search
        subscriptions += repository
                .getAllCharacters(qualifiedSearchQuery)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { view.refresh = true },
                        onSuccess = view::show,
                        onError = view::showError,
                        onFinish = { view.refresh = false }
                )
    }
}