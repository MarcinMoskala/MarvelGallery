@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.MainPresenter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMainView
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMarvelRepository
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacterList
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.fail
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MainPresenterTest {

    /* We need to define trampoline schedulers to be sure that all
       reactive operations will be called before test will finish */
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `Returned list is shown after view was created`() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test
    fun `New list is shown after view was refreshed`() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    @Test
    fun `Error from API is displayed`() {
        // Given
        val someError = Error()
        var errorDisplayed: Throwable? = null
        val view = BaseMainView(
                onShow = { _ -> fail() },
                onShowError = { errorDisplayed = it }
        )
        val marvelRepository = BaseMarvelRepository { Single.error(someError) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        // When
        mainPresenter.onViewCreated()
        // Then
        assertEquals(someError, errorDisplayed)
    }

    @Test
    fun `There is refresh visible when data are loaded`() {
        // Given
        val view = BaseMainView()
        view.refresh = false
        val marvelRepository = BaseMarvelRepository { Single.just(exampleCharacterList) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        view.onShow = { _ ->
            // Then
            assertTrue(view.refresh)
        }
        // When
        mainPresenter.onViewCreated()
        // Then
        assertFalse(view.refresh)
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

        fun thereIsSameListDisplayed() {
            val view = BaseMainView(
                    onShow = { list -> assertEquals(exampleCharacterList, list) },
                    onShowError = { fail() }
            )
            val marvelRepository = BaseMarvelRepository { Single.just(exampleCharacterList) }
            val mainPresenter = MainPresenter(view, marvelRepository)
            mainPresenter.actionOnPresenter()
        }
    }
}