package com.naxtlevelofandroiddevelopment.marvelgallery.network.dto

class CharacterMarvelDto {

    lateinit var name: String
    lateinit var description: String
    lateinit var thumbnail: ImageDto
    var comics: CharacterComicWrapper = CharacterComicWrapper()
    var series: CharacterComicWrapper = CharacterComicWrapper()
    var stories: CharacterComicWrapper = CharacterComicWrapper()
    var events: CharacterComicWrapper = CharacterComicWrapper()

    val imageUrl: String
        get() = thumbnail.completeImagePath
}