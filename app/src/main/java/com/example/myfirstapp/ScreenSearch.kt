package com.example.myfirstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScreenSearch(mainViewModel: MainViewModel, navController: NavController) {
    var filter by remember { mutableStateOf("All") }

    mainViewModel.searchMovies()
    mainViewModel.searchSeries()
    mainViewModel.searchActors()

    val movies by mainViewModel.movies.collectAsState()
    val series by mainViewModel.series.collectAsState()
    val actors by mainViewModel.actors.collectAsState()
    val configuration = LocalConfiguration.current
    val formatTel = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if (formatTel) 2 else 4

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Résultat de la recherche :", style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { filter = "All" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filter == "All") Color(0xFF8A2BE2) else Color(0xFFB19CD9)
                )
            ) {
                Text("All")
            }
            Button(
                onClick = { filter = "Movies" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filter == "Movies") Color(0xFF8A2BE2) else Color(0xFFB19CD9)
                )
            ) {
                Text("Films")
            }
            Button(
                onClick = { filter = "Series" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filter == "Series") Color(0xFF8A2BE2) else Color(0xFFB19CD9)
                )
            ) {
                Text("Series")
            }
            Button(
                onClick = { filter = "Actors" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filter == "Actors") Color(0xFF8A2BE2) else Color(0xFFB19CD9)
                )
            ) {
                Text("Acteurs")
            }
        }

        val filteredMovies = if (filter == "All" || filter == "Movies") movies else emptyList()
        val filteredSeries = if (filter == "All" || filter == "Series") series else emptyList()
        val filteredActors = if (filter == "All" || filter == "Actors") actors else emptyList()

        if (filteredMovies.isEmpty() && filteredSeries.isEmpty() && filteredActors.isEmpty()) {
            Text("Rien ne correspond à votre recherche", modifier = Modifier.padding(top = 32.dp))
        } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize()
        ) {
            when (filter) {
                "All" -> {
                    items(movies) { movie ->
                        MovieCard(movie, navController)
                    }
                    items(series) { serie ->
                        SeriesCard(serie, navController)
                    }
                    items(actors) { actor ->
                        ActorCard(actor, navController)
                    }
                }
                "Movies" -> {
                    items(movies) { movie ->
                        MovieCard(movie, navController)
                    }
                }
                "Series" -> {
                    items(series) { serie ->
                        SeriesCard(serie, navController)
                    }
                }
                "Actors" -> {
                    items(actors) { actor ->
                        ActorCard(actor, navController)
                    }
                }
            }
            }
        }
    }
}

@Composable
fun MovieCard(movie: TmdbMovie, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp)
            .clickable { navController.navigate("filmDetail/${movie.id}") },
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
            Text(text = movie.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = DateFormat(movie.release_date))
        }
    }
}

@Composable
fun SeriesCard(serie: TmdbSerie, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp)
            .clickable { navController.navigate("serieDetail/${serie.id}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = "https://image.tmdb.org/t/p/w342${serie.poster_path}"
            AsyncImage(
                model = imageUrl,
                contentDescription = serie.name
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = serie.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = DateFormat(serie.first_air_date))
        }
    }
}

@Composable
fun ActorCard(actor: TmdbActor, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp)
            .clickable { navController.navigate("acteurDetail/${actor.id}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = "https://image.tmdb.org/t/p/w342${actor.profile_path}"
            AsyncImage(
                model = imageUrl,
                contentDescription = actor.name
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = actor.name, fontWeight = FontWeight.Bold)
        }
    }
}