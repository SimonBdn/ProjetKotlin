package com.example.myfirstapp

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun ScreenActorDetail (mainViewModel: MainViewModel, actorId: String) {
    mainViewModel.getActorDetails(actorId)
    val actors by mainViewModel.actors.collectAsState()
    val actor = actors.find { it.id == actorId }

    actor?.let { actor ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
                AsyncImage(
                    model = imageUrl,
                    contentDescription = actor.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = actor.name, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "ID: ${actor.id}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Date de naissance: ${DateFormat(actor.birthday)}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text= "Biographie : ${actor.biography}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
