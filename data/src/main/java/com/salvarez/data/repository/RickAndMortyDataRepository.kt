package com.salvarez.data.repository

import com.salvarez.data.datasource.RickAndMortyRemoteDataSource
import com.salvarez.domain.RickAndMortyRepository
import com.salvarez.domain.model.RickAndMortyCharacter
import com.salvarez.domain.model.RickAndMortyError
import com.salvarez.domain.model.RickAndMortyResult
import okio.IOException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RickAndMortyDataRepository @Inject constructor(
    private val rickAndMortyRemoteDataSource: RickAndMortyRemoteDataSource
) : RickAndMortyRepository {
    override suspend fun fetchCharacters(): RickAndMortyResult<List<RickAndMortyCharacter>> {
        try {
            val response = rickAndMortyRemoteDataSource.fetchCharacters()
            val rickAndMortyCharactersResponse = response.rickAndMortyCharactersResponse
            if (rickAndMortyCharactersResponse.isNullOrEmpty()) {
                return RickAndMortyResult.Error(
                    RickAndMortyError.EmptyCharacters
                )
            } else {
                val rickAndMortyCharacters =
                    rickAndMortyCharactersResponse.map { rickAndMortyCharacterResponse ->
                        RickAndMortyCharacter(
                            id = rickAndMortyCharacterResponse.id,
                            name = rickAndMortyCharacterResponse.name,
                            status = rickAndMortyCharacterResponse.status,
                            species = rickAndMortyCharacterResponse.species,
                            gender = rickAndMortyCharacterResponse.gender,
                            image = rickAndMortyCharacterResponse.image
                        )
                    }
                return RickAndMortyResult.Success(rickAndMortyCharacters)
            }
        } catch (exception: Exception) {
            return RickAndMortyResult.Error(exception.toRickAndMortyError())
        }
    }

    fun Throwable.toRickAndMortyError(): RickAndMortyError {

        return when (this) {
            is SocketTimeoutException -> {
                RickAndMortyError.Timeout
            }

            is UnknownHostException,
            is ConnectException,
            is IOException -> {
                RickAndMortyError.NoInternet
            }

            is HttpException -> {
                when (code()) {
                    500 ->
                        RickAndMortyError.Server

                    503 ->
                        RickAndMortyError
                            .ServiceUnavailable

                    else ->
                        RickAndMortyError.Unknown
                }
            }

            else ->
                RickAndMortyError.Unknown
        }
    }
}