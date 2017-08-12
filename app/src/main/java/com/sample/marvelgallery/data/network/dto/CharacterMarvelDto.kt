package com.sample.marvelgallery.data.network.dto

class CharacterMarvelDto {

    lateinit var name: String
    lateinit var description: String
    lateinit var thumbnail: ImageDto
    var comics: ListWrapper<ComicDto> = ListWrapper()
    var series: ListWrapper<ComicDto> = ListWrapper()
    var stories: ListWrapper<ComicDto> = ListWrapper()
    var events: ListWrapper<ComicDto> = ListWrapper()

    val imageUrl: String
        get() = thumbnail.completeImagePath
}
