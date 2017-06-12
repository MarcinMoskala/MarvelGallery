@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.network.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.network.Rx
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.main.MainPresenter
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
        checkSearchQuery(null) { onCreate() }
    }

    @Test fun `For blank text, there is request for with null query`() {
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
        MarvelRepository.override = MarvelRepositoryHelper { Single.just(exampleCharacterList) }
        val view = MainViewHelper(
                onShow = { displayedList = it },
                onShowError = { fail() }
        )
        val mainPresenter = MainPresenter(view)
        mainPresenter.onCreate()
        assertEquals(exampleCharacterList, displayedList)
    }

    fun checkSearchQuery(expectedQuery: String?, actionsOnPresenter: MainPresenter.() -> Unit) {
        var checkApplied = false
        MarvelRepository.override = MarvelRepositoryHelper { searchQuery ->
            assertEquals(expectedQuery, searchQuery)
            checkApplied = true
            Single.never()
        }
        val view = MainViewHelper(onShowError = { fail() })
        val mainPresenter = MainPresenter(view)
        mainPresenter.actionsOnPresenter()
        assertTrue(checkApplied)
    }

    fun fail(): Nothing = throw Error("Assertion failed")

    companion object {

        private val exampleCharacterList = listOf(
                MarvelCharacter("Name1", "ImageUrl1", "Description1", listOf("A"), listOf("B"), listOf("C"), listOf("D")),
                MarvelCharacter("Name2", "ImageUrl2", "Description2", listOf("E"), listOf("F"), listOf("G"), listOf("H"))
        )
    }
}