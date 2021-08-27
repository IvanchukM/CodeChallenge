package com.example.codechallenge.repository

import com.example.codechallenge.api.EventApi
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventApi: EventApi) {
    suspend fun getEvents() = eventApi.getEvents()
}