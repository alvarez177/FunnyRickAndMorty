package com.salvarez.funnyrickandmorty.presentation.structuredefinition

import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual

sealed interface RickAndMortyIntent : Reducer.ViewIntent {
    data object ShowLoading : RickAndMortyIntent
    data class ShowRickAndMortyCharacters(val rickAndMortyCharacters: List<RickAndMortyCharacterSupportingVisual>) : RickAndMortyIntent
    data class ShowError(val error: RickAndMortyError, val title: String, val subtitle: String) : RickAndMortyIntent
    data object RetryAction : RickAndMortyIntent
}