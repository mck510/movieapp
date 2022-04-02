package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.testapp.models.Movie

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit = {}
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
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)), elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .size(100.dp)
                .padding(10.dp)) {
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

        }
    }
}