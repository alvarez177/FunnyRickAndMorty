package com.salvarez.funnyrickandmorty.presentation.ui

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.salvarez.funnyrickandmorty.R
import com.salvarez.funnyrickandmorty.model.ErrorAction
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyIntent
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyScreenState
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyUiState

@Composable
fun RickAndMortyCharacterScreen(
    screenState: RickAndMortyScreenState,
    onIntent: (RickAndMortyIntent) -> Unit
) {
    val context = LocalContext.current
    val uiState = screenState.uiState
    Scaffold { paddingValues ->
        when (uiState) {
            RickAndMortyUiState.Loading -> {
                FullScreenLoader(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is RickAndMortyUiState.Error -> {
                val buttonText =
                    if (uiState.errorAction == ErrorAction.Exit) stringResource(R.string.out_button_text) else stringResource(
                        R.string.retry_button_text
                    )
                RickAndMortyErrorScreen(
                    modifier = Modifier.padding(paddingValues),
                    title = uiState.title,
                    subtitle = uiState.subtitle,
                    buttonText = buttonText,
                    onButtonClick = {
                        if (uiState.errorAction == ErrorAction.Exit) {
                            (context as? Activity)?.finish()
                        } else {
                            onIntent(RickAndMortyIntent.RetryClicked)
                        }
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