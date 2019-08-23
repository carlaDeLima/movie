package com.example.carladelima.movie.app.login.business

import com.example.carladelima.movie.app.login.model.User
import com.example.carladelima.movie.app.login.network.UserNetwork
import com.example.carladelima.movie.core.BaseBusiness

object UserBusiness : BaseBusiness() {

    fun login(
        username: String,
        password: String,
        onSuccess: (user: User) -> Unit,
        onError: (msg: Throwable) -> Unit
    ) {
        with(UserNetwork) {
            getAutenticacao(
                { autenticacao ->
                    postSessionWithLogin(username, password, autenticacao.request_token,
                        { sessionWithLogin ->//validaçao

                            postIdSession(sessionWithLogin.request_token,
                                { idSession ->
                                    getAccount(idSession.)
                                },
                                {
                                    onError(it)
                                })

                        },
                        {
                            onError(it)
                        }
                    )
                },
                { error ->
                    onError(error)
                }
            )
        }

    }

}