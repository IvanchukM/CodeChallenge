package com.example.codechallenge.ui.eventsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.repository.EventRepository
import com.example.codechallenge.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventsViewModel @Inject constructor(private val eventRepository: EventRepository) :
    ViewModel() {

    private val _event = MutableLiveData<Resource<List<BaseEventModel?>>>()
    val eventResponse: LiveData<Resource<List<BaseEventModel?>>> get() = _event

    init {
        viewModelScope.launch {
            _event.postValue(Resource.loading(null))
            try {
                _event.postValue(Resource.success(eventRepository.getEvents()))
            } catch (exception: Exception) {
                _event.postValue(Resource.error(null, exception.toString()))
            }
        }
    }

}
