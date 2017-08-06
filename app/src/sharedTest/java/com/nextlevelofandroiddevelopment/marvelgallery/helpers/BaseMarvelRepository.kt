package com.nextlevelofandroiddevelopment.marvelgallery.helpers

import com.naxtlevelofandroiddevelopment.marvelgallery.data.MarvelRepository
import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
        val onGetCharacters: (String?) -> Single<List<MarvelCharacter>>
) : MarvelRepository {

    override fun getAllCharacters(searchQuery: String?) = onGetCharacters(searchQuery)
}