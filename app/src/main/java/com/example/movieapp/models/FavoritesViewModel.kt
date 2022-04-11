package com.example.movieapp.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FavoritesViewModel: ViewModel() {
    private val favoriteMovieList = mutableStateListOf<Movie>()
    val favoriteMovies: List<Movie>
        get() = favoriteMovieList

    fun addMovie(movie: Movie){
        favoriteMovieList.add(movie)
    }
    fun removeMovie(movie: Movie){
        favoriteMovieList.remove(movie)
    }
    fun getAllMovies(): List<Movie>{
        return favoriteMovieList
    }
    fun checkIfFavorite(movie: Movie): Boolean{
        return favoriteMovieList.contains(movie)
    }
}