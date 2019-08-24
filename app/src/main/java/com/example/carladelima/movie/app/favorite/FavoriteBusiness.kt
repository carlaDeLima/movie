package com.example.carladelima.movie.app.favorite

import com.example.carladelima.movie.app.favorite.model.Favorite
import com.example.carladelima.movie.app.favorite.model.MFavoritesResult
import com.example.carladelima.movie.app.login.UserDatabase
import com.example.carladelima.movie.app.login.model.APIResponse
import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.core.BaseBusiness
import com.example.carladelima.movie.core.Paths

object FavoriteBusiness : BaseBusiness() {

    fun favoritar(
        movie: Movie,
        onSuccess: (response: APIResponse) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        val user = UserDatabase.getUser()!!

        FavoriteNetwork.postFavoritar(user.id,
            Paths.api_key, user.session_id, Favorite("movie", movie.id, movie.favorite),
            {
                onSuccess(it)
            },
            {
                onError(it)
            }
        )
    }

    fun moviesFavorite(
        onSuccess: (response: MFavoritesResult) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        val user = UserDatabase.getUser()!!

        FavoriteNetwork.getMoviesFavorite(user.id, Paths.api_key, user.session_id,
            {
                onSuccess(it)
            },
            {
                onError(it)
            }
        )
    }
}