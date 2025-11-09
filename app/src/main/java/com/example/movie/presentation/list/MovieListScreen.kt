package com.example.movie.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
fun MovieListScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onClick: ((Long?) -> Unit)?
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
            columns = GridCells.Fixed(2)
        ) {

            items(
                items = uiState.movieListResponse?.results.orEmpty()
            ) { item ->
                MovieItem(
                    imageUrl = "${BASE_IMAGE_URL}${item?.posterPath}",
                    title = item?.originalTitle,
                    year = item?.releaseDate
                ) {
                    onClick?.invoke(item?.id)
                }
            }
        }

    }

}