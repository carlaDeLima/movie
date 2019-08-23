package com.example.carladelima.movie.app.movie.network

import com.example.carladelima.movie.app.movie.model.MoviesGeral
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    fun getMoviePopular(@Query("api_key") apiKey: String): Observable<MoviesGeral>
}