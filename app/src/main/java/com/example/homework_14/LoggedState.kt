package com.example.homework_14

sealed class LoggedState(){
    data class OnSuccess(val data: List<Items.Content>) : LoggedState()
    object OnEmpty : LoggedState()
    data class IsLoading(val isLoading: Boolean = true) : LoggedState()
    data class OnError(val message: String) : LoggedState()
}
