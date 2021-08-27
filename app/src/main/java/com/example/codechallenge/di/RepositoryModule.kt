package com.example.codechallenge.di

import com.example.codechallenge.api.EventApi
import com.example.codechallenge.repository.EventRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(eventsApi: EventApi): EventRepository = EventRepository(eventsApi)
}