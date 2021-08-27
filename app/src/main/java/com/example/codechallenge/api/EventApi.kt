package com.example.codechallenge.api

import com.example.codechallenge.model.BaseEventModel
import retrofit2.http.GET

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<BaseEventModel>
}