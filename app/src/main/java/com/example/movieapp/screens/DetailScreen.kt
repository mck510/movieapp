package com.example.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@Composable
fun DetailScreen(movieId: String?, navController: NavController = rememberNavController(),viewModel: FavoritesViewModel) {

    val movie = filterMovie(movieId = movieId)

    MainContent(movie.title, navController= navController) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {


        Column(modifier = Modifier
            .padding(9.dp))
           {


        MovieRow(
            movie = movie,viewModel,true)

        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Text(text = "Movie Images", style = MaterialTheme.typography.h5)
        HorizontalScrollableImageView(movie = movie)
           }
    }
    }
}

@Composable
fun MainContent(movieTitle: String, navController: NavController, content: @Composable () -> Unit){
/*
    var showMenu by remember {
        mutableStateOf(false)
    }*/
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp){
                Row{
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack",
                    modifier = Modifier.clickable {
                        navController.popBackStack()

                    })

                    Spacer(modifier = Modifier.width(28.dp))

                    Text(text = movieTitle)
                }
            }
        }

    ) {

        content()
    }
}

fun filterMovie(movieId: String?) : Movie{
    return getMovies().filter { movie ->  movie.id == movieId }[0]
}