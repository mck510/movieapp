package com.example.movieapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.Navigation.MovieScreens
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.HomeScreen
import com.example.movieapp.screens.FavoritesScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(
            route = MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                movieId = backStackEntry.arguments?.getString("movieId"),
                navController = navController
            )
        }
        composable(route = MovieScreens.FavoritesScreen.name) { backStackEntry ->
            FavoritesScreen(navController = navController)
        }
    }
}




