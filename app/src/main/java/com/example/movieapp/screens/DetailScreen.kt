package com.example.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@Composable
fun DetailScreen(navController: NavController, movieID: String?, viewModel: FavoritesViewModel){

    val movie = getMovies().first { it.id == movieID }
    MainContentDetail(movie = movie, navController = navController){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            var favoriteState by remember {
                mutableStateOf(viewModel.checkIfFavorite(movie))
            }
            MovieRow(movie = movie, content = {
                FavoriteIcon(movie, favoriteState = favoriteState, onFavoriteClick = { favMovie ->
                    if(viewModel.checkIfFavorite(favMovie)){
                        viewModel.removeMovie(favMovie)
                        favoriteState = false
                    }
                    else{
                        viewModel.addMovie(favMovie)
                        favoriteState = true
                    }
                })
            })

            Spacer(modifier = Modifier.height(8.dp))
            
            Divider()
            
            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            LazyRow{
                items(movie.images.subList(1, movie.images.size)){ movieImage ->
                    Card(modifier = Modifier
                        .size(240.dp)
                        .padding(8.dp)) {
                        AsyncImage(model = movieImage, contentDescription = "previewImage", modifier = Modifier.clip(RectangleShape), contentScale = ContentScale.Crop)
                    }
                }
            }
        }

    }
}
@Composable
fun MainContentDetail(movie: Movie, navController: NavController, content: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(){
                Row{
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrowBack", modifier = Modifier.clickable { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            }
        }
    ) {
        content()
    }
}
