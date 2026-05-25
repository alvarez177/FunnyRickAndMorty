package com.salvarez.funnyrickandmorty

import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual
import com.salvarez.funnyrickandmorty.presentation.resource.RickAndMortyCharactersResource
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyIntent
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyReducer
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyScreenState
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.RickAndMortyUiState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class RickAndMortyReducerTest {
    private val rickAndMortyCharactersResource: RickAndMortyCharactersResource = mockk()

    private lateinit var reducer: RickAndMortyReducer

    @Before
    fun setup() {
        reducer = RickAndMortyReducer(rickAndMortyCharactersResource)
    }

    @Test
    fun `given any state when ShowLoading intent is received then should update uiState to loading`() {
        val previousState =
            RickAndMortyScreenState(
                uiState = RickAndMortyUiState.Success(
                    data = emptyList()
                )
            )

        val (newState, effect) =
            reducer.reduce(
                previousState = previousState,
                intent = RickAndMortyIntent.ShowLoading
            )

        assertEquals(
            RickAndMortyUiState.Loading,
            newState.uiState
        )

        assertNull(effect)
    }


    @Test
    fun `given characters when ShowRickAndMortyCharacters intent is received then should update success state`() {
        val characters =
            listOf(
                RickAndMortyCharacterSupportingVisual(
                    id = "1",
                    name = "Rick",
                    status = "Alive",
                    image = "image"
                )
            )

        val previousState =
            RickAndMortyScreenState()

        val (newState, effect) =
            reducer.reduce(
                previousState = previousState,
                intent =
                    RickAndMortyIntent.ShowRickAndMortyCharacters(
                        rickAndMortyCharacters = characters
                    )
            )

        assertEquals(
            characters,
            newState.characters
        )

        assertEquals(
            2,
            newState.remainingRetryAttempts
        )

        assertTrue(newState.uiState is RickAndMortyUiState.Success)

        assertNull(effect)
    }

    @Test
    fun `given no retry attempts when ShowError intent is received then should show exit button`() {
        every {
            rickAndMortyCharactersResource.getExitAppButtonText()
        } returns "Salir"

        val previousState =
            RickAndMortyScreenState(
                remainingRetryAttempts = 0
            )

        val (newState, effect) =
            reducer.reduce(
                previousState = previousState,
                intent = RickAndMortyIntent.ShowError(
                    error = RickAndMortyError.Unknown,
                    title = "Error",
                    subtitle = "Subtitle"
                )
            )

        val uiState =
            newState.uiState
                    as RickAndMortyUiState.Error

        assertEquals(
            "Salir",
            uiState.buttonText
        )

        assertNull(effect)
    }
}