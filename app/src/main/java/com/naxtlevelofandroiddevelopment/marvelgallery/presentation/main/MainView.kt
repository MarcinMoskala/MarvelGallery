package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.main

import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter

interface MainView {
    var refresh: Boolean
    fun show(items: List<MarvelCharacter>)
    fun showError(error: Throwable)
}