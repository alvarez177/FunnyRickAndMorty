package com.salvarez.data.di

import com.salvarez.data.network.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val RICK_AND_MORTY_BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @Provides
    @Singleton
    fun provideRickAndMortyRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(RICK_AND_MORTY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyService(
        retrofit: Retrofit
    ): RickAndMortyService {
        return retrofit.create(RickAndMortyService::class.java)
    }
}