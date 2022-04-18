package com.example.movieapp.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.Navigation.MovieScreens
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.MovieRow
import com.example.testapp.models.Movie

val movie1 = filterMovie(movieId = "tt2306299")
val movie2 = filterMovie(movieId = "tt0944947")

@Composable
fun FavoritesScreen(
    navController: NavController = rememberNavController(),
    movieList: List<Movie>,// = listOf(movie1, movie2),
    viewModel: FavoritesViewModel
) {
    MainContent(navController = navController) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Log.d("FavoriteScreen", "test")
            //viewModel.addFavorite(movie1)
            //var list = viewModel.getFavorites()
/*
                Column(modifier = Modifier
                    .padding(9.dp))
                {
                    MovieRow(movie = movie1) { movieId ->
                        navController.navigate(MovieScreens.DetailScreen.name + "/$movieId")
                    }
                    MovieRow(movie = movie2) { movieId ->
                        navController.navigate(MovieScreens.DetailScreen.name + "/$movieId")
                    }
                }
*/

            LazyColumn {
                items(movieList) { movie ->
                    MovieRow(movie = movie, viewModel, false) { movieId ->
                        navController.navigate(MovieScreens.DetailScreen.name + "/$movieId")

                        //Log.d("HomeScreen","$movieId")
                        //test
                    }
                }

            }
        }
    }
}

@Composable
fun MainContent(navController: NavController, content: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                            //navController.navigate(MovieScreens.HomeScreen.name)
                        })

                    Spacer(modifier = Modifier.width(28.dp))

                    Text(text = "Favorites")
                }
            }
        }

    ) {
        content()


    }
}

