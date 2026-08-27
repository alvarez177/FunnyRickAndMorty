package com.salvarez.funnyrickandmorty.presentation.handler

class FatalErrorScreen : ErrorOnFlow() {
    override fun processError(errorConfig: ErrorConfig): ErrorUiModel? {
        return ErrorUiModel.FatalErrorScreen(
            title = errorConfig.customTitle.orEmpty(),
            primaryActionText = errorConfig.customPrimaryActionText.orEmpty(),
            onPrimaryAction = {
                errorConfig.errorFlowActions.onPrimaryAction?.invoke()
            }
        )
    }
}