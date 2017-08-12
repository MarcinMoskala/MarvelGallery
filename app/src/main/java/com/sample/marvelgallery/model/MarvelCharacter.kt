package com.sample.marvelgallery.model

import com.sample.marvelgallery.data.network.dto.CharacterMarvelDto

data class MarvelCharacter(
        val name: String,
        val imageUrl: String
) {

    constructor(dto: CharacterMarvelDto) : this(
            name = dto.name,
            imageUrl = dto.imageUrl
    )
}
