package com.nextlevelofandroiddevelopment.marvelgallery.helpers

import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.main.MainView

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