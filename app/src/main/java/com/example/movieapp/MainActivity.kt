package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation()

                }
            }
        }
            //Log.d("mainActivity", "onCreate")

    }
/*
    override fun onDestroy() {
        super.onDestroy()
        Log.d("mainActivity", "onDestroy")
    }
    override fun onStart() {
        super.onStart()
        Log.d("mainActivity", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("mainActivity", "onResume")
    }
    override fun onStop() {
        super.onStop()
        Log.d("mainActivity", "onStop")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("mainActivity", "onRestart")
    }*/
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        Column() {
            MovieNavigation()
        }
    }
}