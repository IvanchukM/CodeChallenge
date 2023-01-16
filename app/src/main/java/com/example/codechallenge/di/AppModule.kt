package com.example.codechallenge.di

import android.app.Application
import android.content.Context
import com.example.codechallenge.BuildConfig
import com.example.codechallenge.api.EventApi
import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.utils.EventsDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun providesGsonDeserializer(): Gson = GsonBuilder().registerTypeAdapter(
        BaseEventModel::class.java,
        EventsDeserializer
    ).create()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesEventService(retrofit: Retrofit): EventApi =
        retrofit.create(EventApi::class.java)
}