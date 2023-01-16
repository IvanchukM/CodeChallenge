package com.example.codechallenge.repository

import com.example.codechallenge.api.EventApi
import com.example.codechallenge.model.BaseEventModel
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventApi: EventApi) {
    fun getEvents(): Single<List<BaseEventModel>> = eventApi.getEvents()
}