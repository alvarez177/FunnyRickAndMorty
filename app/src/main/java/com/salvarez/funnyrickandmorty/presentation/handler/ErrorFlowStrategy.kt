package com.salvarez.funnyrickandmorty.presentation.handler

enum class ErrorFlowStrategy {
    NETWORK_ERROR_DIALOG,
    RETRY_ERROR_SCREEN,
    EMPTY_CONTENT,
    GENERIC_ERROR
}