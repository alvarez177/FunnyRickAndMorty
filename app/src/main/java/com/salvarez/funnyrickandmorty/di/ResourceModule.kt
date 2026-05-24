package com.salvarez.funnyrickandmorty.di

import com.salvarez.funnyrickandmorty.presentation.resource.RickAndMortyCharactersDataResource
import com.salvarez.funnyrickandmorty.presentation.resource.RickAndMortyCharactersResource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {

    @Singleton
    @Binds
    abstract fun bindResources(resource: RickAndMortyCharactersDataResource): RickAndMortyCharactersResource
}