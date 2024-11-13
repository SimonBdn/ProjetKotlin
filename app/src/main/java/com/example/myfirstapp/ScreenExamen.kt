package com.example.myfirstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScreenExamen(mainViewModel: MainViewModel, navController: NavController) {
    mainViewModel.getHorrorCollections()
    val moviesHorror by mainViewModel.moviesHorror.collectAsState()
    val configuration = LocalConfiguration.current
    val formatTel = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if(formatTel) 2 else 4
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(moviesHorror) { movieH ->
            Card(
                modifier = Modifier.padding(8.dp).height(300.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*val imageUrl = "https://image.tmdb.org/t/p/w342${movieH.poster_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = movie.title
                    )*/
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = movieH.name,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    }
}
