@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.CharacterProfilePresenter
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.BaseCharacterProfileView
import com.nextlevelofandroiddevelopment.marvelgallery.helpers.Example.exampleCharacter
import org.junit.Assert.*
import org.junit.Test

class CharacterPresenterUnitTest {

    @Test
    fun `Image Url is requested to be shown as an image`() {
        var requestedImage: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterImage = {
                    requestedImage = it
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onViewCreated()
        assertEquals(exampleCharacter.imageUrl, requestedImage)
    }

    @Test
    fun `Correct user name and description displayed`() {
        var displayedName: String? = null
        var displayedDescription: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterData = { name, description, _ ->
                    displayedName = name
                    displayedDescription = description
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onViewCreated()
        assertEquals(exampleCharacter.name, displayedName)
        assertEquals(exampleCharacter.description, displayedDescription)
    }

    @Test
    fun `Occurrences field is displaying all data about comics, series, stories and events`() {
        var displayedOccurrences: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterData = { _, _, occurrences ->
                    displayedOccurrences = occurrences
                }
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onViewCreated()
        assertNotNull(displayedOccurrences)
        assertTrue(exampleCharacter.comics.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.series.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.stories.all { it in displayedOccurrences!! })
        assertTrue(exampleCharacter.events.all { it in displayedOccurrences!! })
    }

    @Test
    fun `Occurrences is using correct string ids`() {
        val stringsItShouldUse = listOf(
                R.string.occurrences_comics_list_introduction,
                R.string.occurrences_series_list_introduction,
                R.string.occurrences_events_list_introduction,
                R.string.occurrences_stories_list_introduction
        )
        val idAsString = { id: Int -> "$id" }
        var displayedOccurrences: String? = null
        val view = BaseCharacterProfileView(
                onSetUpCharacterData = { _, _, occurrences ->
                    displayedOccurrences = occurrences
                },
                onGetStringById = idAsString
        )
        val presenter = CharacterProfilePresenter(view, exampleCharacter)
        presenter.onViewCreated()
        assertNotNull(displayedOccurrences)
        assertTrue(stringsItShouldUse.map(idAsString).all { id -> id in displayedOccurrences!! })
    }
}