package com.salvarez.funnyrickandmorty.presentation.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyEffect
import com.salvarez.funnyrickandmorty.presentation.viewmodel.GetRickAndMortyCharactersViewModel

@Composable
fun RickAndMortyCharactersScreenRoute(
    viewModel: GetRickAndMortyCharactersViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val screenState by viewModel.state.collectAsState()
    RickAndMortyCharacterScreen(
        screenState = screenState,
        onRetryAction = viewModel::onRetryClicked
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RickAndMortyEffect.ApplicationExit -> {
                    (context as? Activity)?.finish()
                }
                is RickAndMortyEffect.ReLoadCharacters -> {
                    viewModel.fetchRickAndMortyCharacters()
                }
            }
        }
    }
}