package com.salvarez.funnyrickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.salvarez.funnyrickandmorty.presentation.ui.RickAndMortyCharactersScreenRoute
import com.salvarez.funnyrickandmorty.ui.theme.FunnyRickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FunnyRickAndMorty)
        enableEdgeToEdge()
        setContent {
            FunnyRickAndMortyTheme {
                RickAndMortyCharactersScreenRoute()
            }
        }
    }
}