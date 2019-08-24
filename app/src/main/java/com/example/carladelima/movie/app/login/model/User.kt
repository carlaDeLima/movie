package com.example.carladelima.movie.app.login.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey
    var id: Int = 0,
    var session_id: String = "",
    var name: String = "",
    var username: String = ""
): RealmObject()