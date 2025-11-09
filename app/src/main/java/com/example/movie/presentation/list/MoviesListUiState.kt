package com.example.movie.presentation.list

import com.example.movie.data.remote.model.details.MovieDetailResponse
import com.example.movie.data.remote.model.list.MovieListResponse

data class MoviesListUiState(
    val movieListResponse: MovieListResponse? = null,
    val movieDetailResponse: MovieDetailResponse? = null
)
