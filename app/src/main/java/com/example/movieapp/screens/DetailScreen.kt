package com.example.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun DetailScreen(movieId: String?,navController: NavController = rememberNavController()) {

MainContent()
    {
        Text(text = "Movie $movieId")
    }



}
@Composable
fun MainContent(content: @Composable () -> Unit){

    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp){
                Row{
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack",
                    modifier = Modifier.clickable {
                        //TODO goBack
                        //test3
                    })

                    Spacer(modifier = Modifier.width(28.dp))

                    Text(text = "Movie")
                }
            }
        }

    ) {

        content()
    }
}

