package com.salvarez.funnyrickandmorty.presentation.handler

import com.salvarez.funnyrickandmorty.R

class EmptyContentErrorScreen : ErrorOnFlow() {
    override fun processError(errorConfig: ErrorConfig): ErrorUiModel? {
        return ErrorUiModel.EmptyContent(
            image = R.drawable.image_not_found,
            content = errorConfig.customBody.orEmpty(),
            primaryActionText = errorConfig.customPrimaryActionText.orEmpty(),
            onPrimaryAction = {
                errorConfig.errorFlowActions.onPrimaryAction?.invoke()
            }
        )
    }
}