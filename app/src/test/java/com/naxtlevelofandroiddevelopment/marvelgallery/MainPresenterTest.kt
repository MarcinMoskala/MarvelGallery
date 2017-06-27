@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.MainPresenter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMainView
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMarvelRepository
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacterList
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.fail
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit.SECONDS


class MainPresenterTest {

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.io() }
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
        // Given
        val sem = Semaphore(0)
        val view = BaseMainView(
                onShow = { list ->
                    // Then
                    assertEquals(exampleCharacterList, list)
                    sem.release()
                },
                onShowError = { fail() }
        )
        val marvelRepository = BaseMarvelRepository { Single.just(exampleCharacterList) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        // When
        mainPresenter.onViewCreated()
        sem.tryAcquire(1, SECONDS)
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