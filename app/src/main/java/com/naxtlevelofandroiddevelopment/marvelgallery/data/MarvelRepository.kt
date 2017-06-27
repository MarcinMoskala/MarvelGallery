package com.naxtlevelofandroiddevelopment.marvelgallery.data

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.MarvelApi
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers.retrofit
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.Provider
import io.reactivex.Single

interface MarvelRepository {

    fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>() {
        override fun creator() = MarvelRepositoryImpl(retrofit.create(MarvelApi::class.java))
    }
}