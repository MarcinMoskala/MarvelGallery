package com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers

import com.google.gson.Gson
import com.naxtlevelofandroiddevelopment.marvelgallery.view.common.Provider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitApi : Provider<Retrofit>() {
    override fun creator(): Retrofit = makeRetrofit(makeAddRequiredQueryInterceptor())
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
        .addInterceptor(makeHeadersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .addInterceptor(makeLoggingInterceptor())
        .build()