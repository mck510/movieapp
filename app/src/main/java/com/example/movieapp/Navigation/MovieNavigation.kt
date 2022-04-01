package com.example.movieapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.HomeScreen


@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen"){
            HomeScreen(navController = navController)
        }
        composable(route = "detailscreen/{movieId}",
                arguments = listOf(navArgument("movieId"){
                    type = NavType.StringType
                })
            ) {backStackEntry ->
            DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"),navController = navController)
        }
    }
}




