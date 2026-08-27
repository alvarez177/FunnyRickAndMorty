package com.salvarez.funnyrickandmorty.presentation.handler

import com.salvarez.domain.model.RickAndMortyError
import java.util.EnumMap

class ErrorHandler(
    val errorsOnFlow: EnumMap<ErrorFlowStrategy, ErrorOnFlow>
) {

    fun processError(errorConfig: ErrorConfig): ErrorUiModel? {
        val error = errorConfig.rickAndMortyError
        val strategy = when (errorConfig) {
            RickAndMortyError.NoInternet,
            RickAndMortyError.Timeout -> {
                ErrorFlowStrategy.NETWORK_ERROR_DIALOG
            }

            RickAndMortyError.Server,
            RickAndMortyError.ServiceUnavailable -> {
                ErrorFlowStrategy.RETRY_ERROR_SCREEN
            }

            RickAndMortyError.EmptyCharacters -> {
                ErrorFlowStrategy.EMPTY_CONTENT
            }

            else -> {
                ErrorFlowStrategy.GENERIC_ERROR
            }
        }
        val errorOnFlow = errorsOnFlow[strategy] ?: return null
        return errorOnFlow.processError(errorConfig)
    }
}