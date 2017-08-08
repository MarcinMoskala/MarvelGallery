package com.kotlintest.marvelgallery.helpers

import com.kotlintest.marvelgallery.data.MarvelRepository
import com.kotlintest.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
        val onGetCharacters: (String?) -> Single<List<MarvelCharacter>>
) : MarvelRepository {

    override fun getAllCharacters(searchQuery: String?) = onGetCharacters(searchQuery)
}