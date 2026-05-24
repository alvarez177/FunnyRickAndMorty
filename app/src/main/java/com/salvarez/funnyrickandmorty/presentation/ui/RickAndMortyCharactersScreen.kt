package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyScreenState
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyUiState

@Composable
fun RickAndMortyCharacterScreen(
    screenState: RickAndMortyScreenState,
    onRetryAction: () -> Unit
) {
    val uiState = screenState.uiState
    Scaffold { paddingValues ->
        when (uiState) {
            RickAndMortyUiState.Loading -> {
                FullScreenLoader(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is RickAndMortyUiState.Error -> {
                RickAndMortyErrorScreen(
                    modifier = Modifier.padding(paddingValues),
                    title = uiState.title,
                    subtitle = uiState.subtitle,
                    buttonText = uiState.buttonText,
                    onButtonClick = {
                        onRetryAction()
                    }
                )
            }

            is RickAndMortyUiState.Success -> {
                val rickAndMortyCharacters = uiState.data
                RickAndMortyCharactersContainer(
                    modifier = Modifier.padding(paddingValues),
                    rickAndMortyCharacters = rickAndMortyCharacters
                )
            }
        }
    }
}