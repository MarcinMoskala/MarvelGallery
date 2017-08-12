package com.sample.marvelgallery.data.network.provider

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { makeRetrofit() }

private fun makeRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .client(makeHttpClient())
        .addConverterFactory(GsonConverterFactory.create(Gson())) // 1
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 2
        .build()

private fun makeHttpClient() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS) // 3
        .readTimeout(60, TimeUnit.SECONDS) // 4
        .addInterceptor(makeHeadersInterceptor()) // 5
        .addInterceptor(makeAddSecurityQueryInterceptor()) // 6
        .addInterceptor(makeLoggingInterceptor()) // 7
        .build()
