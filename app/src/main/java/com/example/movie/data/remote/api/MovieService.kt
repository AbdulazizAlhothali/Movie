package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.details.MovieDetailResponse
import com.example.movie.data.remote.model.list.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getMovies(): Result<MovieListResponse?>

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): Result<MovieDetailResponse?>
}