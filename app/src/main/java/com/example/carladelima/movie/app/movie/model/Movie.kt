package com.example.carladelima.movie.app.movie.model

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class Movie(
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var vote_average: Double = 0.0,
    var overview: String = "",
    var release_date: String = "",
    var poster_path: String = "",
    var favorite: Boolean = false
) : RealmObject()