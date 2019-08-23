package com.example.carladelima.movie.app.login.network

import android.annotation.SuppressLint
import com.example.carladelima.movie.app.login.model.AutenticacaoResponse
import com.example.carladelima.movie.app.login.model.User
import com.example.carladelima.movie.core.BaseNetwork
import com.example.carladelima.movie.core.Paths
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
object UserNetwork : BaseNetwork() {

    private val userAPI: UserAPI by lazy {
        UserNetwork.getRetrofitBuilder().build().create(UserAPI::class.java)
    }

    fun getAutenticacao(
        onSuccess: (response: AutenticacaoResponse) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        userAPI.autenticacao(Paths.api_key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(error)
                }
            )
    }

    fun postIdSession(
        requestToken: String, onSuccess: (response: AutenticacaoResponse) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        userAPI.postIdSession(Paths.api_key, requestToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(error)
                }
            )
    }

    fun postSessionWithLogin(
        user: String,
        pass: String,
        requestToken: String,
        onSuccess: (response: AutenticacaoResponse) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        userAPI.sessionWithLogin(Paths.api_key, user, pass, requestToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(error)
                }
            )
    }

    fun getAccount(
        sessionId: String,
        onSuccess: (response: User) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        userAPI.account(Paths.api_key, sessionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(error)
                }
            )
    }

    fun deleteLogout(
        sessionId: String,
        onSuccess: (response: AutenticacaoResponse) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        userAPI.deleteSession(Paths.api_key, sessionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError(error)
                }
            )
    }
}