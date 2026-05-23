package com.salvarez.funnyrickandmorty.presentation.structuredefinition

import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual

data class RickAndMortyScreenState(
    val uiState: RickAndMortyUiState = RickAndMortyUiState.Loading,
    val characters: List<RickAndMortyCharacterSupportingVisual> = emptyList(),
    val remainingRetryAttempts: Int = 2
) : Reducer.ViewState