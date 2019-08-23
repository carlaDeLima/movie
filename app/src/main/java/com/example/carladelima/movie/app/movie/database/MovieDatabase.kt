package com.example.carladelima.movie.app.movie.database

import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.core.BaseDatabase
import io.realm.Realm

object MovieDatabase : BaseDatabase() {

    fun saveMoviePopular(movie: List<Movie>) {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(movie)
            realm.commitTransaction()
        }
    }

    fun searchMovie(id: Int): Movie? {
        val realm = Realm.getDefaultInstance()
        realm
            .where(Movie::class.java)
            .equalTo(Movie::id.name, id)
            .findFirst()?.let {
                return realm.copyFromRealm(it)
            }
        return null
    }
}