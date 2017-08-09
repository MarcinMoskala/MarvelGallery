package com.sample.marvelgallery.view.character

interface CharacterProfileView {
    fun setUpCharacterData(name: String, description: String, photoUrl: String, occurrences: String)
    fun getStringById(id: Int): String
}