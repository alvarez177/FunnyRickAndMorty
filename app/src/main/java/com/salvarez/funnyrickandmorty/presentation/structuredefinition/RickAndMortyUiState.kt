package com.salvarez.funnyrickandmorty.presentation.structuredefinition

import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual

sealed interface RickAndMortyUiState {
    data object Loading : RickAndMortyUiState
    data class  Success(val data: List<RickAndMortyCharacterSupportingVisual>) : RickAndMortyUiState
    data class Error(
        val error: RickAndMortyError,
        val title: String,
        val subtitle: String,
        val buttonText: String
    ) : RickAndMortyUiState
}