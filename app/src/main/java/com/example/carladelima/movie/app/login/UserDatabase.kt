package com.example.carladelima.movie.app.login

import com.example.carladelima.movie.app.login.model.User
import com.example.carladelima.movie.core.BaseDatabase
import io.realm.Realm

object UserDatabase : BaseDatabase() {

    fun saveUser(user: User) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(user)
            realm.commitTransaction()
        }
    }

    fun deleteUser() {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }
    }

    fun getUser(): User? {
        return Realm.getDefaultInstance().where(User::class.java).findFirst()
    }

    fun getIdSession(): String {
        return Realm.getDefaultInstance().where(User::class.java).findFirst()?.session_id ?: ""
    }
}