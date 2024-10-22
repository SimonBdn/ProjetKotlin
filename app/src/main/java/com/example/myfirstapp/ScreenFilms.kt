package com.example.myfirstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun ScreenFilms(mainViewModel: MainViewModel, navController: NavController) {
    mainViewModel.getMovies()
    val movies by mainViewModel.movies.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(movies) { movie ->
            Card(
                modifier = Modifier.padding(8.dp).height(200.dp).clickable {
                    navController.navigate("filmDetail/${movie.id}")
                },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imageUrl = "https://image.tmdb.org/t/p/w154${movie.poster_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = movie.title
                    )
                    Text(text = movie.title)
                    Text(text = DateFormat(movie.release_date))
                }
            }
        }
    }
}
