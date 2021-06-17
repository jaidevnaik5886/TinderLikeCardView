package com.shaadi.peopleinteractive.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaadi.peopleinteractive.utils.SingleLiveEvent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {

    val bus: SingleLiveEvent<BaseEvent> = SingleLiveEvent()

    fun sendEvent(event: BaseEvent) {
        bus.postValue(event)
    }

    //Make network call
    public fun launch(
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                block()
            }
            catch (e: Exception) {
                sendEvent(ErrorEvent(e.message ?: ""))
            }
        }
    }
}