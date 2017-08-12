package com.sample.marvelgallery

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.sample.marvelgallery.view.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun There_is_character_list_visible() {
        val characterNames = listOf("3-D Man", "Abomination (Emil Blonsky)")
        for (name in characterNames) {
            assertIsVisibleText(name)
        }
    }

    fun assertIsVisibleText(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }
}
