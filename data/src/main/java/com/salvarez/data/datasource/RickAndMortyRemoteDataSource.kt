package com.salvarez.data.datasource

import com.salvarez.data.network.RickAndMortyService
import com.salvarez.data.response.RickAndMortyResponse
import javax.inject.Inject

class RickAndMortyRemoteDataSource @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun fetchCharacters(): RickAndMortyResponse {
        return rickAndMortyService.fetchRickAndMortyResponse()
    }
}