package com.example.movie.domain.usecase

import com.example.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Long) = repository.getMovieDetail(movieId)
}