package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.TextField
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction


@Serializable class ProfilHome
@Serializable class DestinationFilms
@Serializable class DestinationSeries
@Serializable class DestinationActeurs



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val mainViewModel: MainViewModel = viewModel()
            var showSearchBar by remember { mutableStateOf(false) }

            Scaffold(
                topBar = {
                    if (showSearchBar) {
                        TextField(
                            value = mainViewModel.searchQuery,
                            onValueChange = { mainViewModel.searchQuery = it },
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            placeholder = { Text("Rechercher...") },
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    mainViewModel.searchMovies()
                                    mainViewModel.searchSeries()
                                    mainViewModel.searchActors()
                                    navController.navigate("ScreenSearch")
                                }
                            )
                        )
                    }
                },
                bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        if(currentDestination?.hasRoute<ProfilHome>()== false) {
                            NavigationBar(containerColor = Color(0xFFFFC0CB),
                                modifier = Modifier.height(64.dp)) {
                            NavigationBarItem(
                                icon = {Icon(painterResource(R.drawable.movie),
                                        contentDescription = "Films")},
                                selected = currentDestination?.hasRoute<DestinationFilms>() == true,
                                onClick = { navController.navigate(DestinationFilms()) })
                            NavigationBarItem(
                                icon = { Icon(painterResource(R.drawable.tv),
                                    contentDescription = "Series")},
                                selected = currentDestination?.hasRoute<DestinationSeries>() == true,
                                onClick = { navController.navigate(DestinationSeries()) })
                            NavigationBarItem(
                                icon = { Icon(painterResource(R.drawable.baseline_person_24),
                                        contentDescription = "Acteurs")},
                                selected = currentDestination?.hasRoute<DestinationActeurs>() == true,
                                onClick = { navController.navigate(DestinationActeurs()) })
                        }
                    }
                },
                floatingActionButton = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    if (currentDestination?.hasRoute<ProfilHome>()== false) {
                        FloatingActionButton(onClick = { showSearchBar = !showSearchBar }) {
                            Icon(painterResource(R.drawable.search), contentDescription = "Recherche")
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
                        ScreenActorDetail(mainViewModel, acteurId, navController)
                        }
                    composable("ScreenSearch") { ScreenSearch(mainViewModel, navController) }
                    }
                }
            }
        }
    }






