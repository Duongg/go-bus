package com.example.gobus.di

import android.content.Context
import com.example.gobus.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    private val BASE_URL = "http://api.bus.danang.gov.vn/"
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

        httpClient.writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)


        httpClient.addInterceptor(HeaderInterceptor(context))
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    inner class HeaderInterceptor(val context: Context): Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("content-type", "application/json")
                .addHeader("Cache-Control","no-cache")
                .addHeader("Connection","close")
                .build()
            return chain.proceed(request)
        }

    }
}