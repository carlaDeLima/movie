package com.example.carladelima.movie.core

import com.google.gson.Gson
import org.json.JSONException
import retrofit2.HttpException
import java.net.HttpURLConnection

class NetworkError(private val exception: Throwable) {

    private var errorBody: NetworkErrorBody? = null
    var code: NetworkErrorCode
    var message: String

    init {

        code = when (exception) {
            is HttpException -> getHttpErrorCode(exception)
            else -> NetworkErrorCode.CONNECTION
        }

        tryConvertErrorBody()

        message = errorBody?.statusMessage ?: code.message

    }

    private fun getHttpErrorCode(httpException: HttpException): NetworkErrorCode {
        return when (httpException.code()) {
            HttpURLConnection.HTTP_BAD_REQUEST -> NetworkErrorCode.BAD_REQUEST
            HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkErrorCode.UNAUTHORIZED
            HttpURLConnection.HTTP_FORBIDDEN -> NetworkErrorCode.FORBIDDEN
            HttpURLConnection.HTTP_NOT_FOUND -> NetworkErrorCode.NOT_FOUND
            in 400..499 -> NetworkErrorCode.CLIENT
            else -> NetworkErrorCode.CONNECTION
        }
    }

    private fun tryConvertErrorBody() {
        if (exception is HttpException) {
            try {
                errorBody = NetworkErrorBody.fromJson(exception.response().errorBody()?.string())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

class NetworkErrorBody(var statusMessage: String) {

    companion object {
        // Decodes json into NetworkErrorBody model object
        fun fromJson(json: String?): NetworkErrorBody? {

            return try {
                Gson().fromJson(json, NetworkErrorBody::class.java)
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }

        }
    }

}

enum class NetworkErrorCode(val message: String) {
    CLIENT("Tente novamente."),
    BAD_REQUEST("Tente novamente."),
    UNAUTHORIZED("Usuário não autorizado."),
    FORBIDDEN("Tente novamente."),
    NOT_FOUND("Não encontrado."),
    CONNECTION("Nao foi possível se conectar ao Movie Finder."),
}