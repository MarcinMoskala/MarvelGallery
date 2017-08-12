package com.sample.marvelgallery.data.network.dto

class CharacterMarvelDto {
    lateinit var name: String // 1
    lateinit var thumbnail: ImageDto // 1

    val imageUrl: String
        get() = thumbnail.completeImagePath
}