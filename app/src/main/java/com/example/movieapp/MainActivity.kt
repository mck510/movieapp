package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.screens.MainContent
import com.example.movieapp.ui.theme.MovieappTheme
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

class MainActivity : ComponentActivity() {
    override fun onStart() {
        super.onStart()
        Log.i("MainActivity","onStart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity","onDestroy()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity","onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity","onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity","onResume()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity","onCreate()")

        val vm: FavoritesViewModel by viewModels()
        val favs = vm.getFavorites()

        setContent {

            MovieappTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation(vm)
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        MovieNavigation()
    }


}
