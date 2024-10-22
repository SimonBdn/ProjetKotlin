package com.example.myfirstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun ScreenActeurs(mainViewModel: MainViewModel, navController: NavController) {
    mainViewModel.getActors()
    val actors by mainViewModel.actors.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )  {
        items(actors) { actor ->
            Card(
                modifier = Modifier.padding(8.dp).height(200.dp).clickable {
                    navController.navigate("acteurDetail/${actor.id}")
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
                    val imageUrl = "https://image.tmdb.org/t/p/w154${actor.profile_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = actor.name
                    )
                    Text(text = actor.name)
                }
            }
        }
    }
}