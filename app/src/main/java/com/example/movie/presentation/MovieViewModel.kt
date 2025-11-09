package com.example.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.usecase.GetMovieDetailUseCase
import com.example.movie.domain.usecase.GetMoviesUseCase
import com.example.movie.presentation.list.MoviesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesListUiState())
    val uiState = _uiState.asStateFlow()
    fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase()
                .onSuccess { movieListResponse ->
                    _uiState.update {
                        it.copy(
                            movieListResponse = movieListResponse
                        )
                    }
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                }
        }
    }

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId)
                .onSuccess { movieDetailResponse ->
                    _uiState.update {
                        it.copy(
                            movieDetailResponse = movieDetailResponse
                        )
                    }
                }
                .onFailure { exception ->
                    exception.printStackTrace()
                }
        }
    }
}