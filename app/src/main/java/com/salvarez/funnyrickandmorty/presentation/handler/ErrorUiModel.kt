package com.salvarez.funnyrickandmorty.presentation.handler

interface ErrorUiModel {
    data class RetryErrorScreen(
        val title: String,
        val actionText: String,
        val onRetry: () -> Unit
    ) : ErrorUiModel
    data class NetworkConnectionDialog(
        val title: String,
        val body: String,
        val primaryActionText: String,
        val secondaryActionText: String,
        val onPrimaryAction: () -> Unit,
        val onSecondaryAction: () -> Unit
    ) : ErrorUiModel
    data class FatalErrorScreen(
        val title: String,
        val primaryActionText: String,
        val onPrimaryAction: () -> Unit
    ) : ErrorUiModel
    data class GenericErrorDialog(
        val title: String,
        val body: String,
        val primaryActionText: String,
        val onPrimaryAction: () -> Unit
    ) : ErrorUiModel
    data class EmptyContent(
        val image: Int,
        val content: String,
        val primaryActionText: String,
        val onPrimaryAction: () -> Unit
    ) : ErrorUiModel

}