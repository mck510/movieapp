package com.example.movieapp.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.movieapp.Navigation.MovieScreens
import com.example.movieapp.R
import com.example.movieapp.screens.filterMovie
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    //navController: NavController.Companion,
    movie: Movie,
    viewModel: FavoritesViewModel,
    icon: Boolean,
    onItemClick: (Any) -> Unit = {},
    //content: @Composable () -> Unit = {}
) {

    var showMore by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp),
            .clickable {
                onItemClick(movie.id)
                //navController.navigate(MovieScreens.DetailScreen.name + "/"+movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)), elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            ) {
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )

            }
            //Text(text = movie.title, fontWeight = FontWeight.Bold)

            Column(
                modifier = Modifier
                    .padding(7.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {

                Text(text = movie.title, fontWeight = FontWeight.Bold)
                Text(text = "Director: " + movie.director, style = MaterialTheme.typography.body2)
                Text(text = "Released: " + movie.year, style = MaterialTheme.typography.body2)


                AnimatedVisibility(visible = showMore) {
                    Column(
                        modifier = Modifier
                            .padding(7.dp)

                    ) {
                        Text(text = "Plot: " + movie.plot, style = MaterialTheme.typography.body2)
                        Divider(
                            modifier = Modifier.padding(3.dp),
                            color = Color.LightGray,
                            thickness = 0.5.dp
                        )
                        Text(text = "Genre: " + movie.genre, style = MaterialTheme.typography.body2)
                        Text(
                            text = "Actors: " + movie.actors,
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = "Rating: " + movie.rating,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

                IconToggleButton(checked = showMore, onCheckedChange = { showMore = it }) {
                    if (showMore) Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Less"
                    )
                    else Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "More"
                    )

                }

            }
            if(icon){
                FavoriteImageButton(movie,viewModel)
            }


        }
    }
}


@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {

    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(200.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.FillBounds,
                    //modifier = Modifier.clip(CircleShape)
                )
            }
        }
    }

}
@Composable
fun FavoriteImageButton(movie:Movie,viewModel: FavoritesViewModel) {
    var showFavorite by remember {
        mutableStateOf(viewModel.checkFavorite(movie))

    }
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.End) {
        IconToggleButton(checked = showFavorite, onCheckedChange = { showFavorite = it

            if(viewModel.checkFavorite(movie)){
                viewModel.removeFavorite(movie)
            }else{
                viewModel.addFavorite(movie)
            }



        }) {
            if (showFavorite) Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "no Favorite",
                //modifier = Modifier.clickable(Log.d("Favorite","Favorite"))
            )
            else Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite"
            )

        }
    }
}