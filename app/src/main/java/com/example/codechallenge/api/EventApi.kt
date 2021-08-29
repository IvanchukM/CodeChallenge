package com.example.codechallenge.api

import com.example.codechallenge.model.BaseEventModel
import io.reactivex.Single
import retrofit2.http.GET

interface EventApi {
    @GET("/events")
    fun getEvents(): Single<List<BaseEventModel>>
}