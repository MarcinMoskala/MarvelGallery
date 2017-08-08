package com.naxtlevelofandroiddevelopment.marvelgallery.view.character

import activitystarter.Arg
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.marcinmoskala.activitystarter.argExtra
import com.naxtlevelofandroiddevelopment.marvelgallery.R
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.presenter.CharacterProfilePresenter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.BaseActivityWithPresenter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.loadImage
import kotlinx.android.synthetic.main.activity_character_profile.*

class CharacterProfileActivity : BaseActivityWithPresenter(), CharacterProfileView {

    @get:Arg val character: MarvelCharacter by argExtra()
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
}
