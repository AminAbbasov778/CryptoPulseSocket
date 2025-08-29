package com.example.cryptoapp.core.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : UIState,Event : UIEvent,Nav : Navigation>: ViewModel() {
    abstract val initialState : State
    private val _state = MutableStateFlow(initialState)
    val state : StateFlow<State> get() = _state.asStateFlow()


    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    val _navigation : Channel<Nav> = Channel()
    val navigation = _navigation.receiveAsFlow()


    fun updateState(update : (State) -> State){
        _state.value = update(_state.value)
    }

    fun setLoading(isLoading : Boolean){
        _isLoading.value = isLoading

    }
    fun navigate(navigation: Nav){
        _navigation.trySend(navigation)
    }
}