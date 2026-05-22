package com.salvarez.domain.model

sealed interface RickAndMortyResult<out T> {
    data class Success<out T>(val data: T) : RickAndMortyResult<T>
    data class Error(
        val error: RickAndMortyError
    ) : RickAndMortyResult<Nothing>
}