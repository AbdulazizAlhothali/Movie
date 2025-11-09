package com.example.movie.data.di

import com.example.movie.data.remote.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieServiceModule {
    @Singleton
    @Provides
    fun provideRemoteMovieService(
        retrofit: Retrofit
    ): MovieService = retrofit.create(MovieService::class.java)
}