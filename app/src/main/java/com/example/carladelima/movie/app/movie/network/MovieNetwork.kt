package com.example.carladelima.movie.app.movie.network

import android.annotation.SuppressLint
import com.example.carladelima.movie.app.movie.model.MoviesGeral
import com.example.carladelima.movie.core.BaseNetwork
import com.example.carladelima.movie.core.NetworkError
import com.example.carladelima.movie.core.Paths
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieNetwork : BaseNetwork() {

    private val movieAPI: MovieAPI by lazy {
        getRetrofitBuilder().build().create(MovieAPI::class.java)
    }

    @SuppressLint("CheckResult")
    fun getMoviePopular(
        onSuccess: (movies: MoviesGeral) -> Unit,
        onError: (error: NetworkError) -> Unit
    ) {
        movieAPI.getMoviePopular(Paths.api_key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(NetworkError(error))
                }
            )
    }
}