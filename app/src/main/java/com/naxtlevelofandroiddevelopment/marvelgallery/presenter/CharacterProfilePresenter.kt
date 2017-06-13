package com.naxtlevelofandroiddevelopment.marvelgallery.presenter

import com.naxtlevelofandroiddevelopment.marvelgallery.R
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.character.CharacterProfileView

class CharacterProfilePresenter(val view: CharacterProfileView, val character: MarvelCharacter) : BasePresenter() {

    private val htmlPoint = "&#8226;"
    private val htmlEnter = "<br/>"

    fun onViewCreated() {
        view.setUpCharacterImage(character.imageUrl)
        view.setUpCharacterData(
                name = character.name,
                description = character.description,
                occurrences = makeOccurrencesText()
        )
    }

    private fun makeOccurrencesText(): String {
        var occurencesText = ""

        fun addListIfNotEmpty(introductionTextId: Int, list: List<String>) {
            if (list.isEmpty()) return
            val introductionText = view.getStringById(introductionTextId)
            val htmlList = list.toHtmlList()
            occurencesText += "$introductionText $htmlEnter $htmlList $htmlEnter"
        }

        addListIfNotEmpty(R.string.occurrences_comics_list_introduction, character.comics)
        addListIfNotEmpty(R.string.occurrences_series_list_introduction, character.series)
        addListIfNotEmpty(R.string.occurrences_stories_list_introduction, character.stories)
        addListIfNotEmpty(R.string.occurrences_events_list_introduction, character.events)

        return occurencesText
    }

    private fun List<String>.toHtmlList(): String = fold("") { acc, item -> "$acc$htmlPoint $item $htmlEnter" }
}

