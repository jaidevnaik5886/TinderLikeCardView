package com.shaadi.peopleinteractive.base

abstract class BaseEvent {

}

data class ToastEvent(val message: String): BaseEvent()
data class ErrorEvent(val message: String): BaseEvent()
data class SuccessEvent(val message: String): BaseEvent()
class NoInternetEvent(): BaseEvent()
class CloseKeyboardEvent(): BaseEvent()