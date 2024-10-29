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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController


@Composable
fun ScreenSeries(mainViewModel: MainViewModel, navController: NavController) {
    mainViewModel.getSeries()
    val series by mainViewModel.series.collectAsState()
    val configuration = LocalConfiguration.current
    val formatTel = configuration.screenWidthDp < configuration.screenHeightDp
    val columns = if(formatTel) 2 else 4

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(series) { serie ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(300.dp)
                    .clickable {
                        navController.navigate("serieDetail/${serie.id}")
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
                    val imageUrl = "https://image.tmdb.org/t/p/w342${serie.poster_path}"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = serie.name
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = serie.name,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = DateFormat(serie.first_air_date))
                }
            }
        }
    }
}


