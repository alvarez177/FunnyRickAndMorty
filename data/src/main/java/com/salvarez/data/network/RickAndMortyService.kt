package com.salvarez.data.network

import com.salvarez.data.response.RickAndMortyResponse
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("character")
    suspend fun fetchRickAndMortyResponse(): RickAndMortyResponse
}