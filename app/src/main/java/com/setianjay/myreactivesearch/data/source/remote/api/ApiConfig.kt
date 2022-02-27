package com.setianjay.myreactivesearch.data.source.remote.api

import com.setianjay.myreactivesearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private const val MAPBOX_URL = "https://api.mapbox.com/geocoding/v5/"
    private const val TIMEOUT = 120L

    private fun getLoggingInterceptor(): Interceptor{
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    private fun getInterceptor(): OkHttpClient{
        return OkHttpClient
            .Builder()
            .addInterceptor(getLoggingInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofit(apiUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())
            .build()
    }

    val mapBoxService: MapBoxService = getRetrofit(MAPBOX_URL).create(MapBoxService::class.java)
}