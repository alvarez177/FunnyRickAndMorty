package com.salvarez.domain.usecase

import com.salvarez.domain.model.RickAndMortyCharacter
import com.salvarez.domain.model.RickAndMortyResult
import com.salvarez.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetRickAndMortyCharactersUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {

    suspend operator fun invoke(): RickAndMortyResult<List<RickAndMortyCharacter>> {
        return rickAndMortyRepository.fetchCharacters()
    }
}