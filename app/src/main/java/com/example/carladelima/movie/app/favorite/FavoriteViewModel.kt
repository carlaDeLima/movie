package com.example.carladelima.movie.app.favorite

import androidx.lifecycle.MutableLiveData
import com.example.carladelima.movie.app.favorite.model.MFavoritesResult
import com.example.carladelima.movie.app.movie.database.MovieDatabase
import com.example.carladelima.movie.core.BaseViewModel
import com.example.carladelima.movie.core.SingleLiveEvent

class FavoriteViewModel : BaseViewModel() {
    val onRequestMovieFavoriteStarted = SingleLiveEvent<Void>()
    val onRequestMovieFavoriteFinished = SingleLiveEvent<Void>()
    //var moviesFavorite = MutableLiveData<Movie>()
    var movieFavorite = MutableLiveData<MFavoritesResult>()

    fun favoritar(id: Int) {
        val movie = MovieDatabase.searchMovie(id)
        movie?.let { m ->
            m.favorite = !m.favorite
            MovieDatabase.saveMovie(m)
            onRequestMovieFavoriteStarted.call()

            FavoriteBusiness.favoritar(m,
                {
                    FavoriteDatabase.save(m)
                    onRequestMovieFavoriteFinished.call()
                },
                {
                    onRequestMovieFavoriteFinished.call()
                }
            )
        }
    }

    fun listMovieFavorite() {
        onRequestMovieFavoriteStarted.call()

        FavoriteBusiness.moviesFavorite(
            onSuccess = {
                movieFavorite.value = it
                onRequestMovieFavoriteFinished.call()
            },
            onError = {
                onRequestMovieFavoriteFinished.call()
            }
        )

    }
}