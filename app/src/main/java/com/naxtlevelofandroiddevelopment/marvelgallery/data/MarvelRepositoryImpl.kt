package com.naxtlevelofandroiddevelopment.marvelgallery.data

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.MarvelApi
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl(val api: MarvelApi) : MarvelRepository {

    override fun getCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
            offset = 0,
            limit = elementsOnListLimit,
            searchQuery = searchQuery
    ).map {
        it.data?.results?.map { MarvelCharacter(it) } ?: emptyList()
    }

    companion object {

        const val elementsOnListLimit = 50
    }
}