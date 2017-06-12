package com.nextlevelofandroiddevelopment.marvelgallery.helpers

import com.naxtlevelofandroiddevelopment.marvelgallery.model.MarvelCharacter
import com.naxtlevelofandroiddevelopment.marvelgallery.network.MarvelRepository
import io.reactivex.Single

class MarvelRepositoryHelper(
        val onGetCharacters: (String?) -> Single<List<MarvelCharacter>>
) : MarvelRepository {

    override fun getCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = onGetCharacters(searchQuery)
}