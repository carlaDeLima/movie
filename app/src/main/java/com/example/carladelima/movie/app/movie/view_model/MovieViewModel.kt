package com.example.carladelima.movie.app.movie.view_model

import androidx.lifecycle.MutableLiveData
import com.example.carladelima.movie.app.movie.business.MovieBusiness
import com.example.carladelima.movie.app.movie.model.MoviesGeral
import com.example.carladelima.movie.core.BaseViewModel
import com.example.carladelima.movie.core.SingleLiveEvent
import com.example.carladelima.movie.core.StringWrapper

class MovieViewModel : BaseViewModel() {
    val onRequestMovieStarted = SingleLiveEvent<Void>()
    val onRequestMovieFinished = SingleLiveEvent<Void>()
    var movies = MutableLiveData<MoviesGeral>()

    fun listMoviePopular() {
        onRequestMovieStarted.call()

        MovieBusiness.listMoviePopular(
            onSuccess = {
                movies.value = it
                onRequestMovieFinished.call()
            },
            onError = {
                onError.value = StringWrapper(it.message)
                onRequestMovieFinished.call()
            }
        )

    }
}