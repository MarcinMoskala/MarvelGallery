package com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers

import android.util.Log
import com.google.gson.Gson
import com.naxtlevelofandroiddevelopment.marvelgallery.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object RetrofitApi : Provider<Retrofit>({
    makeRetrofit(addRequiredQuery())
})

fun makeRetrofit(vararg interceptors: Interceptor): Retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .client(makeHttpClient(interceptors))
        .addConverters()
        .build()

fun Retrofit.Builder.addConverters(): Retrofit.Builder = this
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

private fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .addInterceptor(loggingInterceptor())
        .build()

fun addRequiredQuery() = Interceptor { chain ->
    val originalRequest = chain.request()

    val timeStamp = System.currentTimeMillis()

    val url = originalRequest.url().newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", calculatedMd5AuthParameter(timeStamp))
            .addQueryParameter("ts", "$timeStamp")
            .build()

    // Request customization: add request headers
    val request = originalRequest
            .newBuilder()
            .url(url)
            .build()
    chain.proceed(request)
}

fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}

/**
 * Builds the required API "hash" parameter (timeStamp + privateKey + publicKey)
 * @param timeStamp Current timeStamp
 * @return MD5 hash string
 */
private fun calculatedMd5AuthParameter(timeStamp: Long): String {
    val messageDigest = getMd5Digest(timeStamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)
    val md5 = BigInteger(1, messageDigest).toString(16)
    return "0" * (32 - md5.length) + md5
}

fun getMd5Digest(str: String): ByteArray = try {
    MessageDigest.getInstance("MD5").digest(str.toByteArray())
} catch (e: NoSuchAlgorithmException) {
    Log.e("DataManager", "Error hashing required parameters: " + e.message)
    byteArrayOf()
}

private operator fun String.times(i: Int) = (1..i).fold("", { acc, _ -> acc + this })
