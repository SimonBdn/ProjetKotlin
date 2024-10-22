package com.example.myfirstapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val api_key = "317519a83cc36ab9367ba50e5aa75b40"
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)


    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbSerie>>(listOf())
    val actors = MutableStateFlow<List<TmdbActor>>(listOf())

    fun getMovies(language: String = "fr") {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key, language).results
        }
    }

    fun searchMovies(searchtext: String) {
        viewModelScope.launch {
            movies.value = api.searchmovies(api_key, searchtext).results
        }
    }

    fun getMovieDetails(id: String, language: String = "fr") {
        viewModelScope.launch {
            val movieResult = api.movieDetails(id, api_key, language, "credits")
            val movie = movieResult
            movies.value = listOf(movie)
        }
    }

    fun getSeries(language: String = "fr") {
        viewModelScope.launch {
            series.value = api.lastseries(api_key, language).results
        }
    }

    fun getSerieDetails(id: String, language: String = "fr") {
        viewModelScope.launch {
            val serieResult = api.serieDetails(id, api_key, language, "credits")
            val serie = serieResult
            series.value = listOf(serie)
        }
    }

    fun getActors(language: String = "fr") {
        viewModelScope.launch {
            actors.value = api.lastactors(api_key, language).results
        }
    }

    fun getActorDetails(id: String, language: String = "fr") {
        viewModelScope.launch {
            val actorResult = api.actorDetails(id, api_key, language)
            val actor = actorResult
            actors.value = listOf(actor)
        }
    }



}