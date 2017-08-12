package com.sample.marvelgallery.data

import com.sample.marvelgallery.data.network.MarvelApi
import com.sample.marvelgallery.data.network.provider.retrofit
import com.sample.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl : MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
            offset = 0,
            searchQuery = searchQuery,
            limit = elementsOnListLimit
    ).map {
        it.data?.results?.map(::MarvelCharacter) ?: emptyList() // 1
    }

    companion object {
        const val elementsOnListLimit = 50
    }
}
