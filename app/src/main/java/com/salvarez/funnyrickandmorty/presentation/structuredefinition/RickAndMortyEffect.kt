package com.salvarez.funnyrickandmorty.presentation.structuredefinition

interface RickAndMortyEffect : Reducer.ViewEffect {
    data object ApplicationExit : RickAndMortyEffect
    data object ReLoadCharacters : RickAndMortyEffect
}