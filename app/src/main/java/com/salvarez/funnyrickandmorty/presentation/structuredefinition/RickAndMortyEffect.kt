package com.salvarez.funnyrickandmorty.presentation.structuredefinition

interface RickAndMortyEffect : Reducer.ViewEffect {
    data object RetryAction : RickAndMortyEffect
}