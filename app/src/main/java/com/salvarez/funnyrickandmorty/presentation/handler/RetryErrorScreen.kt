package com.salvarez.funnyrickandmorty.presentation.handler

class RetryErrorScreen : ErrorOnFlow() {
    override fun processError(errorConfig: ErrorConfig): ErrorUiModel? {
        if (apiCallCounter < 2) {
            return ErrorUiModel.RetryErrorScreen(
                title = errorConfig.customTitle.orEmpty(),
                actionText = errorConfig.customPrimaryActionText.orEmpty(),
                onRetry = {
                    errorConfig.errorFlowActions.onRetry?.invoke()
                }
            )
        } else {
            return ErrorUiModel.FatalErrorScreen(
                title = errorConfig.customTitle.orEmpty(),
                primaryActionText = errorConfig.customPrimaryActionText.orEmpty(),
                onPrimaryAction = {
                    errorConfig.errorFlowActions.onPrimaryAction?.invoke()
                }
            )
        }
    }
}