package com.example.movieapp.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.testapp.models.Movie

class FavoritesViewModel : ViewModel() {

    private val favorites = mutableStateListOf<Movie>()

    fun addFavorite(movie: Movie){
        if(!exists(movie)){
            favorites.add(movie)
        }

    }
    fun removeFavorite(movie: Movie){
        favorites.remove(movie)
    }
    fun getFavorites() : List<Movie>{
        return favorites
    }

    private fun exists(movie: Movie): Boolean {

        return favorites.any {m -> m.id == movie.id}
    }
    fun checkFavorite(movie: Movie): Boolean {
        return favorites.contains(movie)

    }
}