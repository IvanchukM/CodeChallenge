package com.example.codechallenge.ui.eventsList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.repository.EventRepository
import com.example.codechallenge.utils.LoadingState
import com.example.codechallenge.utils.handleLoadingState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsViewModel @Inject constructor(
    private val eventRepository: EventRepository
) :
    ViewModel() {

    private val _event = MutableLiveData<List<BaseEventModel?>>()
    val eventResponse: MutableLiveData<List<BaseEventModel?>> get() = _event

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: MutableLiveData<LoadingState> get() = _loadingState

    private val compositeDisposable = CompositeDisposable()

    fun loadEvents() {
        compositeDisposable.add(
            eventRepository.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .handleLoadingState(loadingState)
//                .repeatWhen { completed -> completed.delay(10, TimeUnit.SECONDS) }
                .subscribe({ eventResponse ->
                    this.eventResponse.value = eventResponse
                }, { error ->
                    loadingState.value = LoadingState.Error(throwable = error)
                    Log.e("error:", error.toString(), )
                })
        )
    }


}
