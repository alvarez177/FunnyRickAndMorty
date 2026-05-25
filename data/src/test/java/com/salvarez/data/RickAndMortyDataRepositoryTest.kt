package com.salvarez.data

import com.salvarez.data.datasource.RickAndMortyRemoteDataSource
import com.salvarez.data.repository.RickAndMortyDataRepository
import com.salvarez.data.response.RickAndMortyCharacterResponse
import com.salvarez.data.response.RickAndMortyResponse
import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.domain.model.RickAndMortyResult
import com.salvarez.domain.repository.RickAndMortyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RickAndMortyDataRepositoryTest {
    private val dataSource: RickAndMortyRemoteDataSource = mockk()

    private lateinit var repository: RickAndMortyRepository

    private val rickAndMortyCharacterResponse = RickAndMortyCharacterResponse(
        id = "1",
        name = "Rick Sanchez",
        status = "Status",
        species = "Species",
        gender = "gender",
        image = "image"
    )

    private val charactersResponse = listOf(rickAndMortyCharacterResponse)

    private val rickAndMortySuccessResponse = RickAndMortyResponse(
        rickAndMortyCharactersResponse = charactersResponse,
        info = null
    )

    @Before
    fun setup() {
        repository = RickAndMortyDataRepository(dataSource)
    }

    @Test
    fun `given successful response from datasource when fetchCharacters is called then should return character list`() {
        coEvery {
            dataSource.fetchCharacters()
        } returns rickAndMortySuccessResponse

        val result = runBlocking {
            repository.fetchCharacters()
        }

        assertTrue(result is RickAndMortyResult.Success)

        val successResult =
            result as RickAndMortyResult.Success

        assertEquals(
            1,
            successResult.data.size
        )

        assertEquals(
            "Rick Sanchez",
            successResult.data.first().name
        )

        coVerify(exactly = 1) {
            dataSource.fetchCharacters()
        }
    }

    @Test
    fun `given empty list when fetchCharacters is called then should return EmptyCharacters error`() {
        coEvery {
            dataSource.fetchCharacters()
        } returns RickAndMortyResponse(
            rickAndMortyCharactersResponse = emptyList(),
            info = null
        )

        val result = runBlocking { repository.fetchCharacters() }

        assertEquals(
            RickAndMortyResult.Error(
                RickAndMortyError.EmptyCharacters
            ),
            result
        )
    }

    @Test
    fun `given server error 500 when fetchCharacters is called then should return Server error`() {
            val response =
                Response.error<String>(
                    500,
                    "".toResponseBody(null)
                )

            coEvery {
                dataSource.fetchCharacters()
            } throws HttpException(response)

            val result = runBlocking { repository.fetchCharacters() }

            assertEquals(
                RickAndMortyResult.Error(
                    RickAndMortyError.Server
                ),
                result
            )
        }
}