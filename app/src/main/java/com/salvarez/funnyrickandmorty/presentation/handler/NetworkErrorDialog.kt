package com.salvarez.funnyrickandmorty.presentation.handler

class NetworkErrorDialog : ErrorOnFlow() {
    override fun processError(errorConfig: ErrorConfig): ErrorUiModel? {
        return ErrorUiModel.NetworkConnectionDialog(
            title = errorConfig.customTitle.orEmpty(),
            body = errorConfig.customBody.orEmpty(),
            primaryActionText = errorConfig.customPrimaryActionText.orEmpty(),
            secondaryActionText = errorConfig.customSecondaryActionText.orEmpty(),
            onPrimaryAction = {
                errorConfig.errorFlowActions.onSecondaryAction?.invoke()
            },
            onSecondaryAction = {
                errorConfig.errorFlowActions.onSecondaryAction?.invoke()
            }
        )
    }
}