@file:Suppress("IllegalIdentifier")

package com.kotlintest.marvelgallery

import com.kotlintest.marvelgallery.helpers.BaseCharacterProfileView
import com.kotlintest.marvelgallery.helpers.Example.exampleCharacter
import com.kotlintest.marvelgallery.presenter.CharacterProfilePresenter
import org.junit.Assert.*
import org.junit.Test

class CharacterPresenterTest {

    @Test
    fun `Correct user name and description displayed`() {
        // Given
        var displayedName: String? = null
        var displayedDescription: String? = null
        var requestedImage: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterData = { name, description, image, _ ->
                    displayedName = name
                    displayedDescription = description
                    requestedImage = image
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        // When
        presenter.onViewCreated()
        // Then
        assertEquals(exampleCharacter.name, displayedName)
        assertEquals(exampleCharacter.description, displayedDescription)
        assertEquals(exampleCharacter.imageUrl, requestedImage)
    }

    @Test
    fun `Occurrences field is displaying all data about comics, series, stories and events`() {
        // Given
        var displayedOccurrences: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterData = { _, _, _, occurrences ->
                    displayedOccurrences = occurrences
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        // When
        presenter.onViewCreated()
        // Then
        assertNotNull(displayedOccurrences)
        assertTrue(exampleCharacter.comics.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.series.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.stories.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.events.all { it in displayedOccurrences!! })
    }

    @Test
    fun `Occurrences is using correct string ids`() {
        // Given
        val stringsItShouldUse = listOf(
                R.string.occurrences_comics_list_introduction,
                R.string.occurrences_series_list_introduction,
                R.string.occurrences_events_list_introduction,
                R.string.occurrences_stories_list_introduction
        )
        val idAsString = { id: Int -> "$id" }
        var displayedOccurrences: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterData = { _, _, _, occurrences ->
                    displayedOccurrences = occurrences
                },
                onGetStringById = idAsString
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        // When
        presenter.onViewCreated()
        // Then
        assertNotNull(displayedOccurrences)
        assertTrue(stringsItShouldUse.map(idAsString).all { id -> id in displayedOccurrences!! })
    }
}