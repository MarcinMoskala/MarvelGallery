package com.kotlintest.marvelgallery.helpers

import com.kotlintest.marvelgallery.model.MarvelCharacter
import com.kotlintest.marvelgallery.view.main.MainView

class BaseMainView(
        var onShow: (items: List<MarvelCharacter>) -> Unit = {},
        val onShowError: (error: Throwable) -> Unit = {},
        override var refresh: Boolean = false
) : MainView {

    override fun show(items: List<MarvelCharacter>) {
        onShow(items)
    }

    override fun showError(error: Throwable) {
        onShowError(error)
    }
}