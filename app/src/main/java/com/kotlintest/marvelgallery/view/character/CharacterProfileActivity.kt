package com.kotlintest.marvelgallery.view.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.kotlintest.marvelgallery.R
import com.kotlintest.marvelgallery.model.MarvelCharacter
import com.kotlintest.marvelgallery.presenter.CharacterProfilePresenter
import com.kotlintest.marvelgallery.view.common.BaseActivityWithPresenter
import com.kotlintest.marvelgallery.view.common.extra
import com.kotlintest.marvelgallery.view.common.getIntent
import com.kotlintest.marvelgallery.view.common.loadImage
import kotlinx.android.synthetic.main.activity_character_profile.*

class CharacterProfileActivity : BaseActivityWithPresenter(), CharacterProfileView {

    val character: MarvelCharacter by extra(CHARACTER_ARG)
    override val presenter by lazy { CharacterProfilePresenter(this, character) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_profile)
        setUpToolbar()
        presenter.onViewCreated()
    }

    override fun setUpCharacterData(name: String, description: String, photoUrl: String, occurrences: String) {
        supportActionBar?.title = name
        descriptionView.text = description
        occurrencesView.text = Html.fromHtml(occurrences)
        headerView.loadImage(photoUrl, centerCropped = true)
    }

    override fun getStringById(id: Int) = getString(id) ?: throw Error("No string with such id")

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when {
        item.itemId == android.R.id.home -> onBackPressed().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {

        private val CHARACTER_ARG = "com.naxtlevelofandroiddevelopment.marvelgallery.presentation.heroprofile.CharacterArgKey"

        fun getIntent(context: Context, character: MarvelCharacter): Intent = context
                .getIntent<CharacterProfileActivity>()
                .apply { putExtra(CHARACTER_ARG, character) }

        fun start(context: Context, character: MarvelCharacter) {
            val intent = getIntent(context, character)
            context.startActivity(intent)
        }
    }
}
