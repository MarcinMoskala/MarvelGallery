package com.kotlintest.marvelgallery.data

import com.kotlintest.marvelgallery.data.network.MarvelApi
import com.kotlintest.marvelgallery.data.network.providers.retrofit
import com.kotlintest.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl : MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
            offset = 0,
            limit = elementsOnListLimit,
            searchQuery = searchQuery
    ).map {
        it.data?.results.orEmpty().map(::MarvelCharacter)
    }

    companion object {

        const val elementsOnListLimit = 50
    }
}