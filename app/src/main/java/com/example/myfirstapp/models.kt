package com.example.myfirstapp

data class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf(),
    val cast: List<TmdbMovie> = listOf())

data class TmdbMovie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genres: List<Genre> = listOf(),
    val poster_path: String? = "",
    val credits: Credits = Credits()
)


data class TmdbSerieResult(
    var page: Int = 0,
    val results: List<TmdbSerie> = listOf()
)


data class TmdbSerie(
    var overview: String = "",
    val first_air_date: String = "",
    val id: String = "",
    val name: String = "",
    val original_name: String = "",
    val backdrop_path: String? = "",
    val genres: List<Genre> = listOf(),
    val poster_path: String? = "",
    val credits: Credits = Credits()
    )


data class TmdbActorResult(
    var page: Int = 0,
    val results: List<TmdbActor> = listOf()
)

data class TmdbActor(
    val id: String = "",
    val name: String = "",
    val profile_path: String? = "",
    val biography: String = "",
    val birthday: String = "",
    val deathday: String = "",
)


data class Genre(
    val id: Int,
    val name: String = ""
)

data class Credits(
    val cast: List<Cast> = listOf()
)

data class Cast(
    val id: String = "",
    val name: String = "",
    val profile_path: String? = "",
    val character: String = ""
)

data class HorrorCollection(
    val adult: Boolean,
    val backdrop_path: String?,
    val id: Int,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val poster_path: String?
)

data class HorrorCollectionResponse(
    val page: Int,
    val results: List<HorrorCollection>,
)