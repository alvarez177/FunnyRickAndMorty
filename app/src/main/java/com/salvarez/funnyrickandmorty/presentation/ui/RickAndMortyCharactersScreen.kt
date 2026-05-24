package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.runtime.Composable
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyIntent
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyScreenState
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyUiState

@Composable
fun RickAndMortyCharacterScreen(
    screenState: RickAndMortyScreenState,
    onIntent: (RickAndMortyIntent) -> Unit
) {
    val uiState = screenState.uiState
    when (uiState) {
        RickAndMortyUiState.Loading -> {
            FullScreenLoader()
        }
        is RickAndMortyUiState.Error -> {
            RickAndMortyErrorScreen(
                title = uiState.title,
                subtitle = uiState.subtitle,
                buttonText = "Re intentar",
                onButtonClick = {
                    onIntent(RickAndMortyIntent.RetryClicked)
                }
            )
        }
        is RickAndMortyUiState.Success -> {
            val rickAndMortyCharacters = uiState.data
            RickAndMortyCharactersContainer(rickAndMortyCharacters = rickAndMortyCharacters)
        }
    }
}