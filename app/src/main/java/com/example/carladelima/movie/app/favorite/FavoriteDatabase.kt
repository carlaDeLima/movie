package com.example.carladelima.movie.app.favorite

import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.core.BaseDatabase
import io.realm.Realm

object FavoriteDatabase : BaseDatabase() {

    fun save(movie: Movie) {

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(movie)
            realm.commitTransaction()
        }
    }

    fun remove(id: Int) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.where(Movie::class.java)
                .equalTo(Movie::id.name, id)
                .findFirst()
                ?.deleteFromRealm()
            realm.commitTransaction()
        }
    }

}