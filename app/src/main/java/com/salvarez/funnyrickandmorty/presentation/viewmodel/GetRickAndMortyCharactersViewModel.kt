package com.salvarez.funnyrickandmorty.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.domain.model.RickAndMortyResult
import com.salvarez.domain.usecase.GetRickAndMortyCharactersUseCase
import com.salvarez.funnyrickandmorty.di.IoDispatcher
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual
import com.salvarez.funnyrickandmorty.presentation.handler.ErrorHandler
import com.salvarez.funnyrickandmorty.presentation.resource.RickAndMortyCharactersResource
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyEffect
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyIntent
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyReducer
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetRickAndMortyCharactersViewModel @Inject constructor(
    private val getRickAndMortyCharacters: GetRickAndMortyCharactersUseCase,
    private val resource: RickAndMortyCharactersResource,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    val errorHandler: ErrorHandler
) :
    BaseViewModel<RickAndMortyScreenState, RickAndMortyIntent, RickAndMortyEffect>(
        initialState = RickAndMortyScreenState(),
        reducer = RickAndMortyReducer(resource)
    ) {

    init {
        fetchRickAndMortyCharacters()
    }

    fun fetchRickAndMortyCharacters() {
        viewModelScope.launch {
            sendEvent(RickAndMortyIntent.ShowLoading)
            val rickAndMortyCharactersResult =
                withContext(ioDispatcher) { getRickAndMortyCharacters.invoke() }
            when (rickAndMortyCharactersResult) {
                is RickAndMortyResult.Error -> {
                    when (rickAndMortyCharactersResult.error) {
                        RickAndMortyError.NoInternet,
                        RickAndMortyError.Timeout -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.NoInternet,
                                    title = resource.getNoInternetErrorTitle(),
                                    subtitle = resource.getTryAgainErrorSubtitle()
                                )
                            )
                        }

                        RickAndMortyError.Server,
                        RickAndMortyError.ServiceUnavailable -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.Server,
                                    title = resource.getServerErrorTitle(),
                                    subtitle = resource.getTryAgainErrorSubtitle()
                                )
                            )
                        }

                        RickAndMortyError.EmptyCharacters -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.EmptyCharacters,
                                    title = resource.getNoDataToShowErrorTitle(),
                                    subtitle = resource.getTryAgainErrorSubtitle()
                                )
                            )
                        }

                        RickAndMortyError.Unknown -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.Unknown,
                                    title = resource.getUnknownErrorTitle(),
                                    subtitle = resource.getContactToSupportErrorSubtitle()
                                )
                            )
                        }
                    }
                }

                is RickAndMortyResult.Success -> {
                    val rickAndMortyCharacters = rickAndMortyCharactersResult.data
                    sendEvent(
                        RickAndMortyIntent.ShowRickAndMortyCharacters(
                            rickAndMortyCharacters = rickAndMortyCharacters.map { rickAndMortyCharacter ->
                                RickAndMortyCharacterSupportingVisual(
                                    id = rickAndMortyCharacter.id,
                                    name = rickAndMortyCharacter.name,
                                    status = rickAndMortyCharacter.status,
                                    image = rickAndMortyCharacter.image,
                                )
                            }
                        ))
                }
            }
        }
    }

    fun onRetryClicked() {
        sendEvent(RickAndMortyIntent.RetryClicked)
    }
}