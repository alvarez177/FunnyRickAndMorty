package com.salvarez.funnyrickandmorty.presentation.structuredefinition

import com.salvarez.funnyrickandmorty.presentation.resource.RickAndMortyCharactersResource

class RickAndMortyReducer(
    private val rickAndMortyCharactersResource: RickAndMortyCharactersResource
) :
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
                val buttonText = if (previousState.remainingRetryAttempts > 0) {
                    rickAndMortyCharactersResource.getRetryButtonText()
                } else {
                    rickAndMortyCharactersResource.getExitAppButtonText()
                }
                previousState.copy(
                    uiState = RickAndMortyUiState.Error(
                        error = intent.error,
                        title = intent.title,
                        subtitle = intent.subtitle,
                        buttonText = buttonText
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
                    uiState = RickAndMortyUiState.Success(
                        data = intent.rickAndMortyCharacters
                    ),
                    characters = intent.rickAndMortyCharacters,
                    remainingRetryAttempts = MAX_RETRY_ATTEMPTS
                ) to null
            }

            RickAndMortyIntent.RetryClicked -> {
                val updatedRemainingAttempts = maxOf(0, previousState.remainingRetryAttempts - 1)
                if (previousState.remainingRetryAttempts > 0) {
                    previousState.copy(
                        uiState = RickAndMortyUiState.Loading,
                        remainingRetryAttempts = updatedRemainingAttempts
                    ) to RickAndMortyEffect.ReLoadCharacters
                } else {
                    previousState to RickAndMortyEffect.ApplicationExit
                }
            }
        }
    }
}