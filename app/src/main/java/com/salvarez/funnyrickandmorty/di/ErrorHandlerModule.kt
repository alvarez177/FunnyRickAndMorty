package com.salvarez.funnyrickandmorty.di

import com.salvarez.funnyrickandmorty.presentation.handler.EmptyContentErrorScreen
import com.salvarez.funnyrickandmorty.presentation.handler.ErrorFlowStrategy
import com.salvarez.funnyrickandmorty.presentation.handler.ErrorHandler
import com.salvarez.funnyrickandmorty.presentation.handler.ErrorOnFlow
import com.salvarez.funnyrickandmorty.presentation.handler.GenericErrorDialog
import com.salvarez.funnyrickandmorty.presentation.handler.NetworkErrorDialog
import com.salvarez.funnyrickandmorty.presentation.handler.RetryErrorScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.EnumMap

@Module
@InstallIn(ViewModelComponent::class)
object ErrorHandlerModule {

    @Provides
    @ViewModelScoped
    fun provideErrorHandler(): ErrorHandler {
        val errors: EnumMap<ErrorFlowStrategy, ErrorOnFlow> = EnumMap(
            mapOf(
                ErrorFlowStrategy.RETRY_ERROR_SCREEN to RetryErrorScreen(),
                ErrorFlowStrategy.NETWORK_ERROR_DIALOG to NetworkErrorDialog(),
                ErrorFlowStrategy.EMPTY_CONTENT to EmptyContentErrorScreen(),
                ErrorFlowStrategy.GENERIC_ERROR to GenericErrorDialog()
            )
        )
        return ErrorHandler(errors)
    }

}