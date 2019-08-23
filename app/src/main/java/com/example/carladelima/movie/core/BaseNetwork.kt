package com.example.carladelima.movie.core

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseNetwork {

    private fun getRetrofitBuilder(baseURL: String): Retrofit.Builder {

        val gsonBuilder = getGsonBuilder()

        val retrofitBuilder = Retrofit.Builder()

        with(retrofitBuilder) {
            baseUrl(baseURL)
            addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
            )
        }

        return retrofitBuilder
    }

    fun getRetrofitBuilder(): Retrofit.Builder {
        return getRetrofitBuilder(Paths.urlBase)
    }

    private fun getOkHttpClientBuilder(loggingLevel: HttpLoggingInterceptor.Level): OkHttpClient.Builder {
        val okHttpBuilder = OkHttpClient.Builder()

        with(okHttpBuilder) {

            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)

        }

        return okHttpBuilder

    }

    private fun getGsonBuilder(): GsonBuilder {

        val builder = GsonBuilder()

        with(builder) {
            setExclusionStrategies(object : ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    return f.declaringClass == RealmObject::class.java
                }

                override fun shouldSkipClass(clazz: Class<*>): Boolean {
                    return false
                }
            })
        }

        return builder

    }

}