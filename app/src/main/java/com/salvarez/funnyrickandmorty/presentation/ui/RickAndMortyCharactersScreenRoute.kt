package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyEffect
import com.salvarez.funnyrickandmorty.presentation.viewmodel.GetRickAndMortyCharactersViewModel

@Composable
fun RickAndMortyCharactersScreenRoute(
    viewModel: GetRickAndMortyCharactersViewModel = hiltViewModel()
) {
    val screenState by viewModel.state.collectAsState()
    RickAndMortyCharacterScreen(
        screenState = screenState,
        onIntent = viewModel::onIntent
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RickAndMortyEffect.RetryAction -> {
                    viewModel.fetchRickAndMortyCharacters()
                }
            }
        }
    }
}