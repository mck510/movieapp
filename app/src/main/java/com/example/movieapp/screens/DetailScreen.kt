package com.example.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@Composable
fun DetailScreen(movieId: String?,navController: NavController = rememberNavController()) {

    val movie = filterMovie(movieId = movieId)

    MainContent(movie.title, navController= navController) {


        MovieRow(movie = movie)

        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Text(text = movie.title, style = MaterialTheme.typography.h5)


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
                        //TODO goBack

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