package com.salvarez.funnyrickandmorty.model

import androidx.compose.runtime.Immutable

@Immutable
data class RickAndMortyCharacterSupportingVisual(
    val id: String,
    val name: String,
    val status: String,
    val image: String
)
