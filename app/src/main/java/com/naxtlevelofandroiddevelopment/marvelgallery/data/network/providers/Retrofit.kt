package com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers

import com.google.gson.Gson
import com.naxtlevelofandroiddevelopment.marvelgallery.BuildConfig
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.Provider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitApi : Provider<Retrofit>() {
    override fun creator(): Retrofit = makeRetrofit(addRequiredQuery())
}

private fun makeRetrofit(vararg interceptors: Interceptor): Retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .client(makeHttpClient(interceptors))
        .addConverters()
        .build()

private fun Retrofit.Builder.addConverters(): Retrofit.Builder = this
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

private fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .addInterceptor(loggingInterceptor())
        .build()

private fun addRequiredQuery() = Interceptor { chain ->
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

private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}