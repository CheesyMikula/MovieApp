package com.example.movieapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@Composable
fun HomeScreen(navController: NavController, viewModel: FavoritesViewModel){

    MainContentHome(navController = navController, viewModel)
}

@Composable
fun MainContentHome(navController: NavController, viewModel: FavoritesViewModel, movies: List<Movie> = getMovies()) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = "favoritesscreen") }) {
                            Row {
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite", modifier = Modifier.padding(4.dp))
                                Text(text = "Favorites", modifier = Modifier
                                    .padding(4.dp)
                                    .width(100.dp))
                            }
                        }
                    }
                }
            )
        }
    ) {
        LazyColumn{
            items(movies){ movie ->
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
                    { movieID->
                    navController.navigate(route = "detailscreen/$movieID")
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie, content: @Composable () -> Unit={}, onItemClick: (String) -> Unit={}) {

    var arrowIcon by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .clickable { onItemClick(movie.id) }
        .padding(3.dp)
        .fillMaxWidth()
        .wrapContentHeight(), shape = RoundedCornerShape(corner = CornerSize(8.dp)), elevation = 8.dp) {
        Row(modifier = Modifier
            .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .padding(8.dp)
                .size(100.dp)) {
                AsyncImage(model = movie.images[0], contentDescription = "preview", modifier = Modifier.clip(CircleShape), contentScale = ContentScale.Crop)
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.subtitle1)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.subtitle1)
                AnimatedVisibility(visible = arrowIcon,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    Column(Modifier.padding(5.dp)) {
                        Text(text = "Plot: ${movie.plot}", style = MaterialTheme.typography.body2)
                        Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.body2)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.body2)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.body2)
                    }


                }
                IconButton(onClick = { arrowIcon = !arrowIcon }) {
                    when(arrowIcon){
                        true -> {
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrow down")
                        }
                        false -> {
                            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrow up")
                        }
                    }

                }

            }

            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                content()
            }
        }
    }
}

@Composable
fun FavoriteIcon(movie: Movie, onFavoriteClick: (Movie) -> Unit, favoriteState: Boolean){

    IconButton(onClick = { onFavoriteClick(movie)}) {
        when(favoriteState){
            true -> Icon(imageVector = Icons.Default.Favorite, contentDescription = "favorite")
            false -> Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "no favorite")
        }

    }
}
