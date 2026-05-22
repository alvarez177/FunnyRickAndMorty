package com.salvarez.data.di

import com.salvarez.data.repository.RickAndMortyDataRepository
import com.salvarez.domain.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
   @Binds
    abstract fun bindRickAndMortyRepository(rickAndMortyRepository: RickAndMortyDataRepository): RickAndMortyRepository
}