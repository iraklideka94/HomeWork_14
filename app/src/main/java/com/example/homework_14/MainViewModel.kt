package com.example.homework_14

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class MainViewModel: ViewModel() {
    private val _data = MutableStateFlow<LoggedState>(LoggedState.OnEmpty)
    val data: StateFlow<LoggedState> get() = _data

    fun getInfo(){
        viewModelScope.launch {
            val response = RetrofitInstance.requestItem().getItem()
            if (response.isSuccessful && response.body() != null){
              _data.emit(LoggedState.OnSuccess(response.body()?.content?: emptyList()))
            }
        }
    }


}