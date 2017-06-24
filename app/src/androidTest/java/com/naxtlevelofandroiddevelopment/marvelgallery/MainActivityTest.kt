package com.naxtlevelofandroiddevelopment.marvelgallery

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import com.naxtlevelofandroiddevelopment.marvelgallery.data.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.view.main.MainActivity
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseMarvelRepository
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacterList
import io.reactivex.Single
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun There_is_character_list_visible() {
        startWithDefaultFilteringRepository()
        assertIsVisibleText(exampleCharacter.name)
    }

    @Test
    fun I_see_only_searched_character_after_I_type_its_name_in_search_view() {
        startWithDefaultFilteringRepository()

        onView(withId(R.id.searchView)).perform(replaceText(exampleCharacter.name.take(4)), closeSoftKeyboard())
        assertIsVisibleText(exampleCharacter.name)

        val randomText = "RandomText"
        assert(randomText !in exampleCharacter.name)
        onView(withId(R.id.searchView)).perform(replaceText(randomText), closeSoftKeyboard())
        assertIsNotVisibleText(exampleCharacter.name)
    }

    @Test
    fun On_character_clicked_there_is_character_activity_opened() {
        startWithDefaultFilteringRepository()

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.character_detail_layout)).check(matches(isDisplayed()))
    }

    private fun startWithDefaultFilteringRepository() {
        MarvelRepository.override = BaseMarvelRepository(
                onGetCharacters = { query -> Single.just(exampleCharacterList.filter { query == null || query in it.name }) }
        )
        activityTestRule.launchActivity(Intent())
    }

    fun assertIsVisibleText(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun assertIsNotVisibleText(text: String) {
        try {
            onView(withText(text)).check(matches(not(isDisplayed())))
            assert(false) { "$text is displayed and is should not be" }
        } catch (e: NoMatchingViewException) {
            // View is not in hierarchy
        }
    }
}
