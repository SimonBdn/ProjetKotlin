package com.example.myfirstapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Api TMBd 317519a83cc36ab9367ba50e5aa75b40

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String,
                           @Query("language") langague: String): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchmovies(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String): TmdbMovieResult

    @GET("movie/{id}")
    suspend fun movieDetails(@Path("id") id: String,
                             @Query("api_key") api_key: String,
                             @Query("language") langague: String,
                             @Query("append_to_response") appendToResponse: String = "credits"
        ): TmdbMovie

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String,
                           @Query("language") langague: String): TmdbSerieResult

    @GET("tv/{id}")
    suspend fun serieDetails(@Path("id") id: String,
                             @Query("api_key") api_key: String,
                             @Query("language") langague: String,
                             @Query("append_to_response") appendToResponse: String = "credits"
        ): TmdbSerie

    @GET("search/tv")
    suspend fun searchseries(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String): TmdbSerieResult

    @GET("trending/person/week")
    suspend fun lastactors(@Query("api_key") api_key: String,
                           @Query("language") langague: String): TmdbActorResult

    @GET("person/{id}")
    suspend fun actorDetails(@Path("id") id: String,
                             @Query("api_key") api_key: String,
                             @Query("language") langague: String)
        : TmdbActor

    @GET("search/person")
    suspend fun searchactors(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String): TmdbActorResult

    @GET("person/{id}/movie_credits")
    suspend fun actorMovies(@Path("id") id: String,
                            @Query("api_key") api_key: String,
                            @Query("language") langague: String): TmdbMovieResult

    @GET("search/collection")
    suspend fun searchCollection(@Query("api_key") api_key: String,
                                 @Query("query") searchtext: String,
                                 @Query("language") langague: String): HorrorCollectionResponse
}
