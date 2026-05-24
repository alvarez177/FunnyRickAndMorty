package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual

@Composable
fun RickAndMortyCharactersContainer(
    modifier: Modifier,
    rickAndMortyCharacters: List<RickAndMortyCharacterSupportingVisual>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),

        contentPadding =
            PaddingValues(16.dp),

        horizontalArrangement =
            Arrangement.spacedBy(12.dp),

        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        items(
            rickAndMortyCharacters
        ) { character ->

            RickAndMortyCharacterItem(
                rickAndMortyCharacterSupportingVisual =
                    character
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RickAndMortyCharactersContainerPreview() {
    val rickAndMorty0 = RickAndMortyCharacterSupportingVisual(
        id = "0",
        name = "Rick Sanchez",
        status = "Alive",
        image = ""
    )

    val rickAndMorty1 = RickAndMortyCharacterSupportingVisual(
        id = "1",
        name = "Summer Smith",
        status = "Alive",
        image = ""
    )

    val rickAndMorty2 = RickAndMortyCharacterSupportingVisual(
        id = "2",
        name = "Morty Smith",
        status = "Alive",
        image = ""
    )

    val rickAndMorty3 = RickAndMortyCharacterSupportingVisual(
        id = "3",
        name = "Beth Smith",
        status = "Dead",
        image = ""
    )
    RickAndMortyCharactersContainer(
        modifier = Modifier,
        rickAndMortyCharacters = listOf(
            rickAndMorty0,
            rickAndMorty1,
            rickAndMorty2,
            rickAndMorty3
        )
    )
}