package com.example.myapplication.di

import com.example.myapplication.data.player_names.PlayerNamesRepositoryImpl
import com.example.myapplication.domain.PlayerNamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ApplicationModule {
    @Binds
    @Singleton
    fun providePlayerNamesRepository(
        playerNamesRepositoryImpl: PlayerNamesRepositoryImpl
    ): PlayerNamesRepository
}