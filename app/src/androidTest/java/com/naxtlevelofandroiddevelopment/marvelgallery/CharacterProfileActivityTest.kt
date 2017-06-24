package com.naxtlevelofandroiddevelopment.marvelgallery


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.widget.TextView
import com.naxtlevelofandroiddevelopment.marvelgallery.view.character.CharacterProfileActivity
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class CharacterProfileActivityTest {

    @Rule @JvmField val activityTestRule = ActivityTestRule(CharacterProfileActivity::class.java, false, false)

    @Test
    fun characterActivityTest() {
        val character = Example.exampleCharacter
        activityTestRule.launchActivity(CharacterProfileActivity.getPureIntent(character))

        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
                .check(matches(withText(character.name)))
    }
}
