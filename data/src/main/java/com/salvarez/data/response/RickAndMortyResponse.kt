package com.salvarez.data.response

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse(
    @SerializedName("results")
    val rickAndMortyCharactersResponse: List<RickAndMortyCharacterResponse>?,
    @SerializedName("info")
    val info: RickAndMortyInfoResponse?
)