package com.example.codechallenge.di

import com.example.codechallenge.di.viewModel.ViewModelModule
import com.example.codechallenge.ui.eventDetails.EventDetailsFragment
import com.example.codechallenge.ui.eventsList.EventsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(eventsFragment: EventsFragment)
    fun inject(eventDetailsFragment: EventDetailsFragment)
}
