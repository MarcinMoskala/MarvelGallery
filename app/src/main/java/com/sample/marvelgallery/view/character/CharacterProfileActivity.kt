package com.sample.marvelgallery.view.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.sample.marvelgallery.R
import com.sample.marvelgallery.model.MarvelCharacter
import com.sample.marvelgallery.view.common.BaseActivity
import com.sample.marvelgallery.view.common.extra
import com.sample.marvelgallery.view.common.getIntent
import com.sample.marvelgallery.view.common.loadImage
import kotlinx.android.synthetic.main.activity_character_profile.*

class CharacterProfileActivity : BaseActivity() {

    val character: MarvelCharacter by extra(CHARACTER_ARG)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_profile)
        setUpToolbar()
        supportActionBar?.title = character.name
        descriptionView.text = character.description
        occurrencesView.text = makeOccurrencesText()
        headerView.loadImage(character.imageUrl, centerCropped = true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when {
        item.itemId == android.R.id.home -> onBackPressed().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun makeOccurrencesText(): String {
        var occurrencesText = ""

        fun addListIfNotEmpty(introductionTextId: Int, list: List<String>) {
            if (list.isEmpty()) return
            val introductionText = getString(introductionTextId)
            val listText = list.joinToString (separator = "\n$bullet")
            occurrencesText += "$introductionText\n$listText\n\n"
        }

        addListIfNotEmpty(R.string.occurrences_comics_list_introduction, character.comics)
        addListIfNotEmpty(R.string.occurrences_series_list_introduction, character.series)
        addListIfNotEmpty(R.string.occurrences_stories_list_introduction, character.stories)
        addListIfNotEmpty(R.string.occurrences_events_list_introduction, character.events)

        return occurrencesText
    }

    companion object {
        private const val bullet = '\u2022'
        private const val CHARACTER_ARG = "com.naxtlevelofandroiddevelopment.marvelgallery.presentation.heroprofile.CharacterArgKey"

        fun getIntent(context: Context, character: MarvelCharacter): Intent = context
                .getIntent<CharacterProfileActivity>()
                .apply { putExtra(CHARACTER_ARG, character) }

        fun start(context: Context, character: MarvelCharacter) {
            val intent = getIntent(context, character)
            context.startActivity(intent)
        }
    }
}