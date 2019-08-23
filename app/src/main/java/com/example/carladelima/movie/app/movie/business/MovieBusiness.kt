package com.example.carladelima.movie.app.movie.business

import com.example.carladelima.movie.app.movie.database.MovieDatabase
import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.app.movie.model.MoviesGeral
import com.example.carladelima.movie.app.movie.network.MovieNetwork
import com.example.carladelima.movie.core.BaseBusiness
import com.example.carladelima.movie.core.NetworkError

object MovieBusiness : BaseBusiness() {
    fun listMoviePopular(
            onSuccess: (movies: MoviesGeral) -> Unit,
            onError: (error: NetworkError) -> Unit
    ) {
        MovieNetwork.getMoviePopular(
            {
                MovieDatabase.saveMoviePopular(it.results)
                onSuccess(it)
            },
            { error ->
                onError(error)
            }
        )

    }
}