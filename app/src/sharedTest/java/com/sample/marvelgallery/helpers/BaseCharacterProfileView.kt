package com.sample.marvelgallery.helpers

import com.sample.marvelgallery.view.character.CharacterProfileView

class BaseCharacterProfileView(
        val onSetUpCharacterData: (name: String, description: String, photoUrl: String, occurrences: String) -> Unit = { _, _, _, _ -> },
        val onGetStringById: (id: Int) -> String = { "" }
) : CharacterProfileView {

    override fun setUpCharacterData(name: String, description: String, photoUrl: String, occurrences: String) {
        onSetUpCharacterData(name, description, photoUrl, occurrences)
    }

    override fun getStringById(id: Int): String = onGetStringById(id)
}