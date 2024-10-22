package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute


@Serializable class ProfilHome
@Serializable class DestinationFilms
@Serializable class DestinationSeries
@Serializable class DestinationActeurs

// Api TMBd 317519a83cc36ab9367ba50e5aa75b40


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

            val mainViewModel: MainViewModel = viewModel()


            Scaffold(
                bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        if(currentDestination?.hasRoute<ProfilHome>()== false) {
                            NavigationBar(containerColor = Color(0xFFFFC0CB)) {
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.movie),
                                        contentDescription = "Films"
                                    )
                                },
                                selected = currentDestination?.hasRoute<DestinationFilms>() == true,
                                onClick = { navController.navigate(DestinationFilms()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.tv),
                                        contentDescription = "Series"
                                    )
                                },
                                selected = currentDestination?.hasRoute<DestinationSeries>() == true,
                                onClick = { navController.navigate(DestinationSeries()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.baseline_person_24),
                                        contentDescription = "Acteurs"
                                    )
                                },
                                selected = currentDestination?.hasRoute<DestinationActeurs>() == true,
                                onClick = { navController.navigate(DestinationActeurs()) })
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController, startDestination = ProfilHome(),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable<ProfilHome> { ProfilHome(windowSizeClass, navController) }
                    composable<DestinationFilms> { ScreenFilms(mainViewModel, navController) }
                    composable<DestinationSeries> { ScreenSeries(mainViewModel, navController) }
                    composable<DestinationActeurs> { ScreenActeurs(mainViewModel, navController) }
                    composable("filmDetail/{filmId}") { backStackEntry ->
                        val filmId =
                            backStackEntry.arguments?.getString("filmId") ?: return@composable
                        ScreenFilmDetail(mainViewModel, filmId, navController)
                    }
                    composable("serieDetail/{serieId}") { backStackEntry ->
                        val serieId = backStackEntry.arguments?.getString("serieId")?.toString() ?: return@composable
                        ScreenSerieDetail(mainViewModel, serieId, navController)
                        }
                    composable("acteurDetail/{acteurId}") { backStackEntry ->
                        val acteurId = backStackEntry.arguments?.getString("acteurId")?.toString()
                            ?: return@composable
                        ScreenActorDetail(mainViewModel, acteurId)
                        }
                    }
                }
            }
        }
    }

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
                            Image(
                                painter = painterResource(R.drawable.baseline),
                                contentDescription = "un mail",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Column {
                            Text(text = "simonbo@orange.fr")
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
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
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

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentSize(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column {
                                    Image(
                                        painter = painterResource(R.drawable.telicon),
                                        contentDescription = "un téléphone",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Image(
                                        painter = painterResource(R.drawable.baseline),
                                        contentDescription = "un mail",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                Column {
                                    Text(text = "simonbo@orange.fr")
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

