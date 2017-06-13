package com.naxtlevelofandroiddevelopment.marvelgallery.data

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.MarvelApi
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers.Provider
import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers.RetrofitApi
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {

    fun getCharacters(searchQuery: String?): Single<List<MarvelCharacter>>

    class Impl(val api: MarvelApi) : MarvelRepository {

        override fun getCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
                offset = 0,
                limit = elementsOnListLimit,
                searchQuery = searchQuery
        ).map {
            it.data?.results?.map { MarvelCharacter(it) } ?: emptyList()
        }
    }

    companion object : Provider<MarvelRepository>({
        RetrofitApi.get().create(MarvelApi::class.java).let { Impl(it) }
    }) {
        const val elementsOnListLimit = 50
    }
}