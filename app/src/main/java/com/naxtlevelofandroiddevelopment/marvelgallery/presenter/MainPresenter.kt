package com.naxtlevelofandroiddevelopment.marvelgallery.presenter

import com.naxtlevelofandroiddevelopment.marvelgallery.data.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.applySchedulers
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.plusAssign
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.smartSubscribe
import com.naxtlevelofandroiddevelopment.marvelgallery.view.main.MainView

class MainPresenter(val view: MainView) : BasePresenter() {

    private val repository by MarvelRepository.lazyGet()

    fun onViewCreated() {
        loadCharacters()
    }

    fun onSearchChanged(newText: String) {
        loadCharacters(newText)
    }

    private fun loadCharacters(search: String? = null) {
        val qualifiedSearchQuery = if (search.isNullOrBlank()) null else search
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