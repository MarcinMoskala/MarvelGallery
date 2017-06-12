package com.naxtlevelofandroiddevelopment.marvelgallery


import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import com.naxtlevelofandroiddevelopment.marvelgallery.network.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.main.MainActivity
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacterList
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.MarvelRepositoryHelper
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
    fun mainActivityTest() {
        MarvelRepository.override = MarvelRepositoryHelper(
                onGetCharacters = { query -> Single.just(exampleCharacterList.filter { query == null || query in it.name }) }
        )

        activityTestRule.launchActivity(Intent())
        assertIsVisibleText(exampleCharacter.name)

        onView(withId(R.id.searchView)).perform(replaceText(exampleCharacter.name.take(4)), closeSoftKeyboard())
        assertIsVisibleText(exampleCharacter.name)

        val randomText = "RandomText"
        assert(randomText !in exampleCharacter.name)
        onView(withId(R.id.searchView)).perform(replaceText(randomText), closeSoftKeyboard())
        assertIsNotVisibleText(exampleCharacter.name)
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
