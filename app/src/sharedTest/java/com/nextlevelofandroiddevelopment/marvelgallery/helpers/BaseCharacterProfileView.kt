package com.nextlevelofandroiddevelopment.marvelgallery.helpers

import com.naxtlevelofandroiddevelopment.marvelgallery.view.character.CharacterProfileView

class BaseCharacterProfileView(
        val onSetUpCharacterImage: (photoUrl: String) -> Unit = {},
        val onSetUpCharacterData: (name: String, description: String, occurrences: String) -> Unit = { n, d, o -> },
        val onGetStringById: (id: Int) -> String = { "" }
) : CharacterProfileView {

    override fun setUpCharacterImage(photoUrl: String) {
        onSetUpCharacterImage(photoUrl)
    }

    override fun setUpCharacterData(name: String, description: String, occurrences: String) {
        onSetUpCharacterData(name, description, occurrences)
    }

    override fun getStringById(id: Int): String = onGetStringById(id)
}