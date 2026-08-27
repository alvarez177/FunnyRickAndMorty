package com.salvarez.funnyrickandmorty.presentation.handler

data class ErrorFlowActions(
    val onRetry: (() -> Unit)? = null,
    val onPrimaryAction: (() -> Unit)? = null,
    val onSecondaryAction: (() -> Unit)? = null,

)