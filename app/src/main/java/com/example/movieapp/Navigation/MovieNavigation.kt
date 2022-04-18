package com.example.movieapp


import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.Navigation.MovieScreens
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.HomeScreen
import com.example.movieapp.screens.FavoritesScreen
import com.example.movieapp.viewmodels.FavoritesViewModel

@Composable
fun MovieNavigation(vm: FavoritesViewModel = viewModel()) {
    val navController = rememberNavController()
    //val vm: FavoritesViewModel = viewModel()
    val favs = vm.getFavorites()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController,vm)
        }
        composable(
            route = MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                movieId = backStackEntry.arguments?.getString("movieId"),
                navController = navController,
                vm
            )
        }
        composable(route = MovieScreens.FavoritesScreen.name) { backStackEntry ->
            FavoritesScreen(navController = navController,favs,vm)
        }
    }
}




