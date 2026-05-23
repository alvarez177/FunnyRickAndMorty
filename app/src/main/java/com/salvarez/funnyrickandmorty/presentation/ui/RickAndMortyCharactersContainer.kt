package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual

@Composable
fun RickAndMortyCharactersContainer(
    rickAndMortyCharacters: List<RickAndMortyCharacterSupportingVisual>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(rickAndMortyCharacters.chunked(2)) { rowItems ->
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                rowItems.forEach { character ->
                    RickAndMortyCharacterItem(character)
                }
            }
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
        rickAndMortyCharacters = listOf(
            rickAndMorty0,
            rickAndMorty1,
            rickAndMorty2,
            rickAndMorty3
        )
    )
}