package com.example.mekotlin58.di

import com.example.mekotlin58.data.NewRepository
import com.example.mekotlin58.data.remote.apiservises.NewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideNewsRepository(newApi: NewApi) = NewRepository(newApi)
}