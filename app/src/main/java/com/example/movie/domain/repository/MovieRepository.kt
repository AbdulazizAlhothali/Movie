package com.example.movie.domain.repository

import com.example.movie.data.remote.model.details.MovieDetailResponse
import com.example.movie.data.remote.model.list.MovieListResponse

interface MovieRepository {

    suspend fun getMovies(): Result<MovieListResponse?>

    suspend fun getMovieDetail(movieId: Long): Result<MovieDetailResponse?>
}