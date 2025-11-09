package com.example.movie.presentation.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movie.data.di.Constants.BASE_IMAGE_URL
import com.example.movie.presentation.MovieViewModel
import com.example.movie.presentation.component.MovieItem

@Composable
fun MovieDetailScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    id: Long?
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(id ?: 0)
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            item {
                MovieItem(
                    imageUrl = "${BASE_IMAGE_URL}${uiState.movieDetailResponse?.posterPath}",
                    title = uiState.movieDetailResponse?.originalTitle,
                    year = uiState.movieDetailResponse?.releaseDate,
                    details = uiState.movieDetailResponse?.overview
                )
            }
        }
    }
}