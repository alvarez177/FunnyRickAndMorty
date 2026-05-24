package com.salvarez.funnyrickandmorty.presentation.resource

interface RickAndMortyCharactersResource {
    fun getNoInternetErrorTitle(): String
    fun getServerErrorTitle(): String
    fun getNoDataToShowErrorTitle(): String
    fun getUnknownErrorTitle(): String
    fun getTryAgainErrorSubtitle(): String
    fun getContactToSupportErrorSubtitle(): String
}