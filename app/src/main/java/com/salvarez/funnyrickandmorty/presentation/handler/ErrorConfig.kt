package com.salvarez.funnyrickandmorty.presentation.handler

import com.salvarez.domain.model.RickAndMortyError

data class ErrorConfig(
    val rickAndMortyError: RickAndMortyError,
    val customTitle: String? = null,
    val customBody: String? = null,
    val customPrimaryActionText: String? = null,
    val customSecondaryActionText: String? = null,
    val errorFlowStrategy: ErrorFlowStrategy = ErrorFlowStrategy.RETRY_ERROR_SCREEN,
    val errorFlowActions: ErrorFlowActions
)
