package com.example.carladelima.movie.app.login.business

import com.example.carladelima.movie.app.login.UserDatabase
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
        var id: String
        with(UserNetwork) {
            getAutenticacao(
                { autenticacao ->
                    postSessionWithLogin(username, password, autenticacao.request_token,
                        { sessionWithLogin ->

                            postIdSession(sessionWithLogin.request_token,
                                { idSession ->
                                    id = idSession.session_id
                                    getAccount(idSession.session_id,
                                        {
                                            it.session_id = id
                                            onSuccess(it)
                                        },
                                        {
                                            onError(it)//usuario
                                        })
                                },
                                {
                                    onError(it)//sessao
                                })

                        },
                        {
                            onError(it)//validação
                        }
                    )
                },
                { error ->
                    onError(error)//autenticação
                }
            )
        }

    }

    fun logout(
        onSuccess: () -> Unit,
        onError: (msg: Throwable) -> Unit){
        UserNetwork.deleteLogout(UserDatabase.getIdSession(),
            {
                onSuccess()
            },
            {
                onError(it)
            }
        )
    }

}