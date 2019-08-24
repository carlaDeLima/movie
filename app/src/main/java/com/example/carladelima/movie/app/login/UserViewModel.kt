package com.example.carladelima.movie.app.login

import com.example.carladelima.movie.app.login.business.UserBusiness
import com.example.carladelima.movie.core.BaseViewModel
import com.example.carladelima.movie.core.SingleLiveEvent

class UserViewModel : BaseViewModel() {
    val onLoginSucess = SingleLiveEvent<Void>()
    val onLogoutSucess = SingleLiveEvent<Void>()
    val onErro = SingleLiveEvent<String>()
    val onUserLogged = SingleLiveEvent<Void>()

    fun login(user: String, password: String) {
        UserBusiness.login(user, password,
            {
                UserDatabase.saveUser(it)
                onLoginSucess.call()
            },
            {
                onErro.value = it.message
            })
    }

    fun logout() {
        UserBusiness.logout(
            {
                UserDatabase.deleteUser()
                onLogoutSucess.call()
            },
            {
                onErro.value = it.message
            }
        )
    }
}