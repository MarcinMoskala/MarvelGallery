@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.presentation.character.CharacterProfilePresenter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.CharacterProfileViewHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterPresenterUnitTest {

    @Test fun `Image Url is requested to be shown as an image`() {
        var requestedImage: String? = null
        val view = CharacterProfileViewHelper(
                onSetUpCharacterImage = {
                    requestedImage = it
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onStart()
        assertEquals(exampleCharacter.imageUrl, requestedImage)
    }

    @Test fun `Correct user name and description displayed`() {
        var displayedName: String? = null
        var displayedDescription: String? = null
        val view = CharacterProfileViewHelper(
                onSetUpCharacterData = { name, description, _ ->
                    displayedName = name
                    displayedDescription = description
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onStart()
        assertEquals(exampleCharacter.name, displayedName)
        assertEquals(exampleCharacter.description, displayedDescription)
    }

    @Test fun `Occurrences field is displaying all data about comics, series, stories and events`() {
        var displayedOccurrences: String? = null
        val view = CharacterProfileViewHelper(
                onSetUpCharacterData = { _, _, occurrences ->
                    displayedOccurrences = occurrences
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onStart()
        assertTrue(displayedOccurrences != null)
        assertTrue(exampleCharacter.comics.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.series.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.stories.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.events.all { it in displayedOccurrences!! })
    }

    @Test fun `Occurrences is using correct string ids`() {
        val stringsItShouldUse = listOf(
                R.string.occurrences_comics_list_introduction,
                R.string.occurrences_series_list_introduction,
                R.string.occurrences_events_list_introduction,
                R.string.occurrences_stories_list_introduction
        )
        val idAsString = { id: Int -> "$id" }
        var displayedOccurrences: String? = null
        val view = CharacterProfileViewHelper(
                onSetUpCharacterData = { _, _, occurrences ->
                    displayedOccurrences = occurrences
                },
                onGetStringById = idAsString
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onStart()
        assertTrue(displayedOccurrences != null)
        assertTrue(stringsItShouldUse.map(idAsString).all { id -> id in displayedOccurrences!! })
    }

    companion object {

        private val exampleCharacter = MarvelCharacter("Name1", "ImageUrl1", "Description1", listOf("A", "E", "GHI"), listOf("B", "F", "JK LM"), listOf("C"), listOf("D"))
    }
}