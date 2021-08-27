package com.example.codechallenge.ui.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.DetailsModel
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(): ViewModel() {

    private val _detailsData = MutableLiveData<DetailsModel>()
    val detailsData: LiveData<DetailsModel> get() = _detailsData

    fun loadDetailsInfo(detailsModel: DetailsModel) {
        _detailsData.postValue(detailsModel)
    }
}