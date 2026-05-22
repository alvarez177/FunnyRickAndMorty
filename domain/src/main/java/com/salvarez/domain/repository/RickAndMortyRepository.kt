package com.salvarez.domain.repository

import com.salvarez.domain.model.RickAndMortyCharacter
import com.salvarez.domain.model.RickAndMortyResult

interface RickAndMortyRepository {

    suspend fun fetchCharacters(): RickAndMortyResult<List<RickAndMortyCharacter>>
}