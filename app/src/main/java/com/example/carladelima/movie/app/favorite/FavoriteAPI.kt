package com.example.carladelima.movie.app.favorite

import com.example.carladelima.movie.app.favorite.model.Favorite
import com.example.carladelima.movie.app.favorite.model.MFavoritesResult
import com.example.carladelima.movie.app.login.model.APIResponse
import io.reactivex.Observable
import retrofit2.http.*

interface FavoriteAPI {

    @POST("account/{account_id}/favorite")
    fun postFavoritar(
        @Path("account_id") accountId: Int,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String,
        @Body favorite: Favorite,
        @Header("Content-Type") contentType:String = "application/json;charset=utf-8"
    ): Observable<APIResponse>


    @GET("account/{account_id}/favorite/movies")
    fun getMoviesFavorite(
        @Path("account_id") accountId: Int,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): Observable<MFavoritesResult>
}