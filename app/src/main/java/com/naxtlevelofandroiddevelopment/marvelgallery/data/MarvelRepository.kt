package com.naxtlevelofandroiddevelopment.marvelgallery.data

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.MarvelApi
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers.RetrofitApi
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.Provider
import io.reactivex.Single

interface MarvelRepository {

    fun getCharacters(searchQuery: String?): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>({
        MarvelRepositoryImpl(RetrofitApi.get().create(MarvelApi::class.java))
    })
}