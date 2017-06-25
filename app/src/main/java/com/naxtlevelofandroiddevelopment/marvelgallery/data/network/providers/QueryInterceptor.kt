package com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers

import com.naxtlevelofandroiddevelopment.marvelgallery.BuildConfig
import okhttp3.Interceptor

fun makeAddRequiredQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()

    val timeStamp = System.currentTimeMillis()

    val url = originalRequest.url().newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", calculatedMd5AuthParameter(timeStamp, BuildConfig.PRIVATE_KEY, BuildConfig.PUBLIC_KEY))
            .addQueryParameter("ts", "$timeStamp")
            .build()

    // Request customization: add request headers
    val request = originalRequest
            .newBuilder()
            .url(url)
            .build()
    chain.proceed(request)
}