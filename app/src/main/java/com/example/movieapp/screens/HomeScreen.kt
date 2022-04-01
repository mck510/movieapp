package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.widgets.MovieRow

import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    MainContent(navController = navController)
}


@Composable
fun MainContent(navController: () -> Unit, movieList: () -> Unit = getMovies()) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") }, actions = {

                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Favorite, contentDescription = "Favorites",
                            modifier = Modifier
                                .padding(4.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                        Text(
                            text = "Favorites",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(120.dp)
                        )
                    }
                }
            })
        }

    ) {

        LazyColumn {
            items(movieList) { movie ->
                MovieRow(movie,onItemClick = {movieId ->
                    navController.navigate("detailscreen/$movieId")
                    //Log.d("HomeScreen","$movieId")
                })
            }
        }
    }
}


