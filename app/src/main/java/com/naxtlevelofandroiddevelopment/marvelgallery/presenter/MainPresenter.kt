package com.naxtlevelofandroiddevelopment.marvelgallery.presenter

import com.naxtlevelofandroiddevelopment.marvelgallery.data.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.applySchedulers
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.plusAssign
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.smartSubscribe
import com.naxtlevelofandroiddevelopment.marvelgallery.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) : BasePresenter() {

    fun onViewCreated() {
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
                .retry()
                .smartSubscribe(
                        onStart = { view.refresh = true },
                        onSuccess = view::show,
                        onError = view::showError,
                        onFinish = { view.refresh = false }
                )
    }
}