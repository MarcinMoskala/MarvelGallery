package com.naxtlevelofandroiddevelopment.marvelgallery.network

import com.naxtlevelofandroiddevelopment.marvelgallery.network.dto.CharacterMarvelDto
import com.naxtlevelofandroiddevelopment.marvelgallery.network.dto.DataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(
            @Query("offset") offset: Int?,
            @Query("limit") limit: Int?,
            @Query("nameStartsWith") searchQuery: String?
    ): Single<DataWrapper<List<CharacterMarvelDto>>>
}
