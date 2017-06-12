package com.naxtlevelofandroiddevelopment.marvelgallery.presentation.character;

interface CharacterProfileView {
    fun setUpCharacterImage(photoUrl: String)
    fun setUpCharacterData(name: String, description: String, occurrences: String)
    fun getStringById(id: Int): String
}