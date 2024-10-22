package com.example.myfirstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun ScreenSerieDetail(mainViewModel: MainViewModel, serieId: String, navController: NavController) {
    mainViewModel.getSerieDetails(serieId)
    val series by mainViewModel.series.collectAsState()
    val serie = series.find { it.id == serieId }
    val columns = 2

    serie?.let { serie ->
        val genreNames = serie.genres.joinToString(", ") { it.name }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item(span = { GridItemSpan(columns) }) {
                Column {
                    val imageUrl = "https://image.tmdb.org/t/p/w500${serie.poster_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = serie.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = serie.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Date de sortie: ${DateFormat(serie.first_air_date)}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Genres : $genreNames", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Résumé : ${serie.overview}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Acteurs :", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            items(serie.credits.cast) { cast ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(200.dp)
                        .clickable { navController.navigate("acteurDetail/${cast.id}") },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val imageUrl = "https://image.tmdb.org/t/p/w154${cast.profile_path}"
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = cast.name
                        )
                        Text(text = cast.character)
                        Text(text = cast.name)
                    }
                }
            }
        }
    }
}