package com.example.myfirstapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun ProfilHome(windowClass: WindowSizeClass, navController: NavController) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Simon BODIN",
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.size(16.dp))

                Image(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = "un potit chat",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(2.dp, Color.Black), CircleShape)
                        .scale(1.25f)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Image(
                            painter = painterResource(R.drawable.telicon),
                            contentDescription = "un téléphone",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Image(
                            painter = painterResource(R.drawable.baseline),
                            contentDescription = "un mail",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column {
                        Text(text = "simonbo@orange.fr")
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = "06 00 90 00 69")
                    }
                }

                Spacer(modifier = Modifier.size(32.dp))
                Button(onClick = { navController.navigate(DestinationFilms()) }) {
                    Text(text = "Démarrer")
                }
            }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Simon BODIN",
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        Image(
                            painter = painterResource(R.drawable.chat),
                            contentDescription = "un potit chat",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                                .border(BorderStroke(2.dp, Color.Black), CircleShape)
                                .scale(1.25f)
                        )


                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }

                item{
                    Column {
                        Spacer(modifier= Modifier.size(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.telicon),
                                    contentDescription = "un téléphone",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Image(
                                    painter = painterResource(R.drawable.baseline),
                                    contentDescription = "un mail",
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Column {
                                Text(text = "simonbo@orange.fr")
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(text = "06 00 90 00 69")
                            }
                        }

                        Spacer(modifier = Modifier.size(32.dp))
                        Button(onClick = { navController.navigate(DestinationFilms()) }) {
                            Text(text = "Démarrer")
                        }
                    }
                }
            }
        }
    }
}