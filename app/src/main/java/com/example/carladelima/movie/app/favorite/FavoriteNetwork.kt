package com.example.carladelima.movie.app.favorite

import android.annotation.SuppressLint
import com.example.carladelima.movie.app.favorite.model.Favorite
import com.example.carladelima.movie.app.favorite.model.MFavoritesResult
import com.example.carladelima.movie.app.login.model.APIResponse
import com.example.carladelima.movie.core.BaseNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
object FavoriteNetwork : BaseNetwork() {
    private val favoriteAPI: FavoriteAPI by lazy {
        getRetrofitBuilder().build().create(FavoriteAPI::class.java)
    }

    fun postFavoritar(
        accountId: Int,
        apiKey: String,
        sessionId: String,
        favorite: Favorite,
        onSuccess: (response: APIResponse) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        favoriteAPI.postFavoritar(accountId, apiKey, sessionId, favorite)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(error)
                }
            )
    }

    fun getMoviesFavorite(
        accountId: Int,
        apiKey: String,
        sessionId: String,
        onSuccess: (response: MFavoritesResult) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        favoriteAPI.getMoviesFavorite(accountId, apiKey, sessionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess(it)
                },
                { error ->
                    onError(error)
                }
            )
    }
}