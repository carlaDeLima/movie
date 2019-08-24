package com.example.carladelima.movie.app.movie.business

import com.example.carladelima.movie.app.favorite.FavoriteNetwork
import com.example.carladelima.movie.app.login.UserDatabase
import com.example.carladelima.movie.app.movie.database.MovieDatabase
import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.app.movie.model.MoviesGeral
import com.example.carladelima.movie.app.movie.network.MovieNetwork
import com.example.carladelima.movie.core.BaseBusiness
import com.example.carladelima.movie.core.NetworkError
import com.example.carladelima.movie.core.Paths

object MovieBusiness : BaseBusiness() {
    fun listMoviePopular(
        onSuccess: (movies: MoviesGeral) -> Unit,
        onError: (error: NetworkError) -> Unit
    ) {
        MovieNetwork.getMoviePopular(
            { popular ->
                val listPopular = popular.results
                val user = UserDatabase.getUser()!!
                FavoriteNetwork.getMoviesFavorite(user.id, Paths.api_key, user.session_id,
                    { favorite ->
                        val listFavorite = favorite.results
                        listPopular.forEach { p ->
                            listFavorite.forEach { f ->
                                if (p.id == f.id) {
                                    p.favorite = true
                                }
                            }
                        }
                        MovieDatabase.saveMoviePopular(listPopular)
                    },
                    {

                    })
                MovieDatabase.saveMoviePopular(listPopular)
                onSuccess(popular)
            },
            { error ->
                onError(error)
            }
        )

    }
}