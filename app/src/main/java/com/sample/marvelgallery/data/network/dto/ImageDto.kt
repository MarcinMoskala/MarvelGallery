package com.sample.marvelgallery.data.network.dto

class ImageDto {

    lateinit var path: String // 1
    lateinit var extension: String // 1

    val completeImagePath: String
        get() = "$path.$extension"
}
