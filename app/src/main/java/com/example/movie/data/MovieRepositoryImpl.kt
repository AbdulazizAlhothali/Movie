package com.example.movie.data

import com.example.movie.data.remote.api.MovieService
import com.example.movie.data.remote.model.details.MovieDetailResponse
import com.example.movie.data.remote.model.list.MovieListResponse
import com.example.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieService
) : MovieRepository {
    override suspend fun getMovies(): Result<MovieListResponse?> {
        return apiService.getMovies()
    }

    override suspend fun getMovieDetail(movieId: Long): Result<MovieDetailResponse?> {
        return apiService.getMovieDetail(movieId)
    }
}