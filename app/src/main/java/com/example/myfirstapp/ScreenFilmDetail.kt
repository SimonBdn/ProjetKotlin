package com.example.myfirstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun ScreenFilmDetail(mainViewModel: MainViewModel, filmId: String, navController: NavController) {
    mainViewModel.getMovieDetails(filmId)
    val movies by mainViewModel.movies.collectAsState()
    val movie = movies.find { it.id == filmId }
    val configuration = LocalConfiguration.current
    val formatTel = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if(formatTel) 2 else 4

    movie?.let { film ->
        val genreNames = film.genres.joinToString(", ") { it.name }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item(span = { GridItemSpan(columns) }) {
                Column {
                    val imageUrl = "https://image.tmdb.org/t/p/w500${film.poster_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = film.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = film.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Date de sortie: ${DateFormat(film.release_date)}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Genres : $genreNames", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = film.overview?.takeIf { it.isNotEmpty() } ?: "Résumé non disponible",
                         style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Acteurs :", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            items(film.credits.cast) { cast ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(300.dp)
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
                        val imageUrl = "https://image.tmdb.org/t/p/w342${cast.profile_path}"
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = cast.name
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cast.character, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cast.name)
                    }
                }
            }
        }
    }
}