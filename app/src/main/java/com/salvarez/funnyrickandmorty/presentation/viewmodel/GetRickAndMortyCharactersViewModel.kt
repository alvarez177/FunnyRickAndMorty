package com.salvarez.funnyrickandmorty.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.domain.model.RickAndMortyResult
import com.salvarez.domain.usecase.GetRickAndMortyCharactersUseCase
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyEffect
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyIntent
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyReducer
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetRickAndMortyCharactersViewModel @Inject constructor(
    private val getRickAndMortyCharacters: GetRickAndMortyCharactersUseCase
) :
    BaseViewModel<RickAndMortyScreenState, RickAndMortyIntent, RickAndMortyEffect>(
        initialState = RickAndMortyScreenState(),
        reducer = RickAndMortyReducer()
    ) {

    init {
        fetchRickAndMortyCharacters()
    }

    fun fetchRickAndMortyCharacters() {
        viewModelScope.launch {
            sendEvent(RickAndMortyIntent.ShowLoading)
            val rickAndMortyCharactersResult = getRickAndMortyCharacters.invoke()
            when (rickAndMortyCharactersResult) {
                is RickAndMortyResult.Error -> {
                    when (rickAndMortyCharactersResult) {
                        RickAndMortyError.NoInternet,
                        RickAndMortyError.Timeout -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.NoInternet,
                                    title = "Error de conexión a internet ó expiro el tiempo de la solicitud",
                                    subtitle = "Intentalo mas tarde."
                                )
                            )
                        }

                        RickAndMortyError.Server,
                        RickAndMortyError.ServiceUnavailable -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.Server,
                                    title = "Error de conexión con el servidor",
                                    subtitle = "Intentalo mas tarde."
                                )
                            )
                        }

                        RickAndMortyError.EmptyCharacters -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.EmptyCharacters,
                                    title = "No se encontrarón caracteres",
                                    subtitle = "Intentalo mas tarde."
                                )
                            )
                        }

                        RickAndMortyError.Unknown -> {
                            sendEvent(
                                event = RickAndMortyIntent.ShowError(
                                    RickAndMortyError.Unknown,
                                    title = "Error desconocido",
                                    subtitle = "Contacte a soporte."
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
}