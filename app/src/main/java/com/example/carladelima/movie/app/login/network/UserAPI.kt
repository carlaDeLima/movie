package com.example.carladelima.movie.app.login.network

import com.example.carladelima.movie.app.login.model.AutenticacaoResponse
import com.example.carladelima.movie.app.login.model.User
import io.reactivex.Observable
import retrofit2.http.*

interface UserAPI {
    @GET("authentication/token/new")
    fun autenticacao(@Query("api_key") apiKey: String): Observable<AutenticacaoResponse>

    @POST("authentication/session/new")
    fun postIdSession(
        @Query("api_key") apiKey: String,
        @Field("request_token") requestToken: String
    ): Observable<AutenticacaoResponse>

    @POST("authentication/token/validate_with_login")
    fun sessionWithLogin(
        @Query("api_key") apiKey: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("request_token") requestToken: String
    ): Observable<AutenticacaoResponse>

    @GET("account")
    fun account(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): Observable<User>

    @DELETE("authentication/session")
    fun deleteSession(
        @Query("api_key") apiKey: String,
        @Field("session_id") sessionId: String
    ): Observable<AutenticacaoResponse>
}