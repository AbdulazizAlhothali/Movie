package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movie.presentation.details.MovieDetailScreen
import com.example.movie.presentation.list.MovieListScreen
import com.example.movie.presentation.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MovieRout.MoviesListRout
                ) {
                    composable<MovieRout.MoviesListRout> {
                        MovieListScreen {
                            navController.navigate(MovieRout.MovieDetailRout(it))
                        }
                    }

                    composable<MovieRout.MovieDetailRout> {
                        val args = it.toRoute<MovieRout.MovieDetailRout>()
                        MovieDetailScreen(id = args.id)
                    }
                }

            }
        }
    }
}

object MovieRout {
    @Serializable
    data object MoviesListRout

    @Serializable
    data class MovieDetailRout(val id: Long?)
}