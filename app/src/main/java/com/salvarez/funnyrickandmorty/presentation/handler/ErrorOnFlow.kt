package com.salvarez.funnyrickandmorty.presentation.handler

abstract class ErrorOnFlow(
    val apiCallCounter: Int = 0
) {
    abstract fun processError(errorConfig: ErrorConfig): ErrorUiModel?
}