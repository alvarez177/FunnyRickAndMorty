package com.salvarez.domain.model

sealed interface RickAndMortyError {

    // HTTP / Network
    data object NoInternet : RickAndMortyError
    data object Timeout : RickAndMortyError
    data object Server : RickAndMortyError
    data object ServiceUnavailable : RickAndMortyError
    data object Unknown : RickAndMortyError

    // Business
    data object EmptyCharacters : RickAndMortyError
}