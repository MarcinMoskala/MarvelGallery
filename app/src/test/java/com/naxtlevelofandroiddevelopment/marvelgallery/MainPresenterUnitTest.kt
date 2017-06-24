@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.Rx
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.MainPresenter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMainView
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMarvelRepository
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacterList
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.fail
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

    @Test
    fun `After start, there is request for with null query`() {
        assertOnAction { onViewCreated() } searchQueryIsEqualTo null
    }

    @Test
    fun `For blank text, there is request with null query`() {
        assertOnAction { onSearchChanged("") } searchQueryIsEqualTo null
        assertOnAction { onSearchChanged("   ") } searchQueryIsEqualTo null
        assertOnAction { onSearchChanged("         ") } searchQueryIsEqualTo null
    }

    @Test
    fun `Search query is the same as provided text when not blank`() {
        for (text in listOf("KKO", "HJ HJ", "And so what?"))
            assertOnAction { onSearchChanged(text) } searchQueryIsEqualTo text
    }

    @Test
    fun `Returned list is shown`() {
        var displayedList: List<MarvelCharacter>? = null
        val view = BaseMainView(
                onShow = { displayedList = it },
                onShowError = { fail() }
        )
        val marvelRepository = BaseMarvelRepository { Single.just(exampleCharacterList) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        mainPresenter.onViewCreated()
        assertEquals(exampleCharacterList, displayedList)
    }

    private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

        infix fun searchQueryIsEqualTo(expectedQuery: String?) {
            var checkApplied = false
            val view = BaseMainView(onShowError = { fail() })
            val marvelRepository = BaseMarvelRepository { searchQuery ->
                assertEquals(expectedQuery, searchQuery)
                checkApplied = true
                Single.never()
            }
            val mainPresenter = MainPresenter(view, marvelRepository)
            mainPresenter.actionOnPresenter()
            assertTrue(checkApplied)
        }
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)
}