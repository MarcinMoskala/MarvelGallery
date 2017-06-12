package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.main

import com.naxtlevelofandroiddevelopment.marvelgallery.network.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.network.applySchedulers
import com.naxtlevelofandroiddevelopment.marvelgallery.network.smartSubscribe
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.common.Presenter

class MainPresenter(val view: MainView) : Presenter() {

    private val repository by MarvelRepository.lazyGet()

    override fun onCreate() {
        loadCharacters()
    }

    fun onSearchChanged(newText: String) {
        loadCharacters(newText)
    }

    private fun loadCharacters(search: String? = null) {
        val qualifiedSearchQuery = if (search == null || search.isBlank()) null else search
        subscriptions += repository
                .getCharacters(qualifiedSearchQuery)
                .applySchedulers()
                .retry()
                .smartSubscribe(
                        onSuccess = view::show,
                        onError = view::showError,
                        onFinish = { view.refresh = false }
                )
    }
}