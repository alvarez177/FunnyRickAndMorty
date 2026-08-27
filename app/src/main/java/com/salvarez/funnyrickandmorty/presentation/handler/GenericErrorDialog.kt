package com.salvarez.funnyrickandmorty.presentation.handler

class GenericErrorDialog : ErrorOnFlow() {

    override fun processError(errorConfig: ErrorConfig): ErrorUiModel? {
        return ErrorUiModel.GenericErrorDialog(
            title = errorConfig.customTitle.orEmpty(),
            body = errorConfig.customBody.orEmpty(),
            primaryActionText = errorConfig.customPrimaryActionText.orEmpty(),
            onPrimaryAction = {
                errorConfig.errorFlowActions.onSecondaryAction?.invoke()
            }
        )
    }
}