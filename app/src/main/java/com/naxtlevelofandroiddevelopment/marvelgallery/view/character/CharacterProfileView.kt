package com.naxtlevelofandroiddevelopment.marvelgallery.view.character;

interface CharacterProfileView {
    fun setUpCharacterImage(photoUrl: String)
    fun setUpCharacterData(name: String, description: String, occurrences: String)
    fun getStringById(id: Int): String
}