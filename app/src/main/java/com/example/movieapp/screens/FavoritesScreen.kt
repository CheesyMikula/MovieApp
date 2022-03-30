package com.example.movieapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies


@Composable
fun FavoritesScreen(navController: NavController){

    val favoritesList = listOf(getMovies()[2], getMovies()[4], getMovies()[5])

    MainContentFavorites(navController = navController){
        LazyColumn{
            items(favoritesList){ movie ->
                MovieRow(movie = movie)
            }
        }

    }
}

@Composable
fun MainContentFavorites(navController: NavController, content: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(){
                Row{
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrowBack", modifier = Modifier.clickable { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "My Favorite Movies")
                }
            }
        }
    ) {
        content()
    }
}
