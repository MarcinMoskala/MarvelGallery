package com.nextlevelofandroiddevelopment.marvelgallery.helpers

import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.main.MainView

class MainViewHelper(
        val onShow: (items: List<MarvelCharacter>) -> Unit = {},
        val onShowError: (error: Throwable) -> Unit = {}
) : MainView {

    override var refresh: Boolean = false

    override fun show(items: List<MarvelCharacter>) {
        onShow(items)
    }

    override fun showError(error: Throwable) {
        onShowError(error)
    }
}