package com.salvarez.funnyrickandmorty.presentation.structuredefinition

import com.salvarez.funnyrickandmorty.model.ErrorAction
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyUiState.Error
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyUiState.Success

class RickAndMortyReducer :
    Reducer<RickAndMortyScreenState, RickAndMortyIntent, RickAndMortyEffect> {

    companion object {
        private const val MAX_RETRY_ATTEMPTS = 2
    }

    override fun reduce(
        previousState: RickAndMortyScreenState,
        intent: RickAndMortyIntent
    ): Pair<RickAndMortyScreenState, RickAndMortyEffect?> {
        return when (intent) {
            is RickAndMortyIntent.ShowError -> {
                val errorAction = if (previousState.remainingRetryAttempts > 0) {
                    ErrorAction.Retry
                } else {
                    ErrorAction.Exit
                }
                previousState.copy(
                    uiState = Error(
                        error = intent.error,
                        title = intent.title,
                        subtitle = intent.subtitle,
                        errorAction = errorAction
                    )
                ) to null
            }

            RickAndMortyIntent.ShowLoading -> {
                previousState.copy(
                    uiState = RickAndMortyUiState.Loading
                ) to null
            }

            is RickAndMortyIntent.ShowRickAndMortyCharacters -> {
                previousState.copy(
                    uiState = Success(
                        data = intent.rickAndMortyCharacters
                    ),
                    characters = intent.rickAndMortyCharacters,
                    remainingRetryAttempts = MAX_RETRY_ATTEMPTS
                ) to null
            }

            RickAndMortyIntent.RetryAction -> {
                val updatedRemainingAttempts = maxOf(0, previousState.remainingRetryAttempts - 1)
                previousState.copy(
                    uiState = RickAndMortyUiState.Loading,
                    remainingRetryAttempts = updatedRemainingAttempts
                ) to RickAndMortyEffect.RetryAction
            }
        }
    }
}