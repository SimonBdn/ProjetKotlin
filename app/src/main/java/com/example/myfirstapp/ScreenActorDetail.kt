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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun ScreenActorDetail(mainViewModel: MainViewModel, actorId: String, navController: NavController) {
    mainViewModel.getActorDetails(actorId)
    mainViewModel.getActorMovies(actorId)
    val actors by mainViewModel.actors.collectAsState()
    val movies by mainViewModel.movies.collectAsState()
    val actor = actors.find { it.id == actorId }
    val configuration = LocalConfiguration.current
    val formatTel = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if(formatTel) 2 else 4

    actor?.let { actor ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item(span = { GridItemSpan(columns) }) {
                Column {
                    val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = actor.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = actor.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = actor.birthday?.let { "Date de naissance: ${DateFormat(it)}" } ?: "Date de naissance non disponible",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = actor.biography?.takeIf { it.isNotEmpty() } ?: "Biographie non disponible",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Connu pour :",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (movies.isNotEmpty()) {
                items(movies) { movie ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(300.dp)
                            .clickable {
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
                            val imageUrl = "https://image.tmdb.org/t/p/w342${movie.poster_path}"
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = movie.title
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = movie.title,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = DateFormat(movie.release_date))
                        }
                    }
                }
            } else {
                item(span = { GridItemSpan(columns) }) {
                    Text(
                        text = "Pas de film trouvé pour cet acteur.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}