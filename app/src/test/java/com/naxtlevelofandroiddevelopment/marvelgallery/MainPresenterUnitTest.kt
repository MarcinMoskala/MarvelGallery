@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.Rx
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.MainPresenter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacterList
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.MainViewHelper
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.MarvelRepositoryHelper
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MainPresenterUnitTest {

    @Before
    fun setUp() {
        Rx.unitTestMode = true
    }

    @Test fun `After start, there is request for with null query`() {
        checkSearchQuery(null) { onViewCreated() }
    }

    @Test fun `For blank text, there is request with null query`() {
        checkSearchQuery(null) { onSearchChanged("") }
        checkSearchQuery(null) { onSearchChanged("   ") }
        checkSearchQuery(null) { onSearchChanged("       ") }
    }

    @Test fun `Search query is the same as provided text when not blank`() {
        for (text in listOf("KKO", "HJ HJ", "And so what?"))
            checkSearchQuery(text) { onSearchChanged(text) }
    }

    @Test fun `Returned list is shown`() {
        var displayedList: List<MarvelCharacter>? = null
        val view = MainViewHelper(
                onShow = { displayedList = it },
                onShowError = { fail() }
        )
        val marvelRepository = MarvelRepositoryHelper { Single.just(exampleCharacterList) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        mainPresenter.onViewCreated()
        assertEquals(exampleCharacterList, displayedList)
    }

    fun checkSearchQuery(expectedQuery: String?, actionsOnPresenter: MainPresenter.() -> Unit) {
        var checkApplied = false
        val view = MainViewHelper(onShowError = { fail() })
        val marvelRepository = MarvelRepositoryHelper { searchQuery ->
            assertEquals(expectedQuery, searchQuery)
            checkApplied = true
            Single.never()
        }
        val mainPresenter = MainPresenter(view, marvelRepository)
        mainPresenter.actionsOnPresenter()
        assertTrue(checkApplied)
    }

    fun fail(): Nothing = throw Error("Assertion failed")
}