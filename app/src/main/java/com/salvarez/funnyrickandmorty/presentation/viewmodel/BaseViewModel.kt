package com.salvarez.funnyrickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.salvarez.funnyrickandmorty.presentation.structuredefinition.Reducer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : Reducer.ViewState, ViewIntent : Reducer.ViewIntent, Effect : Reducer.ViewEffect>(
    private val initialState: State,
    private val reducer: Reducer<State, ViewIntent, Effect>
) : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(value = initialState)
    val state: StateFlow<State> get() = _state.asStateFlow()

    private val _event: MutableSharedFlow<ViewIntent> = MutableSharedFlow()
    val event: SharedFlow<ViewIntent> get() = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel(capacity = Channel.Factory.CONFLATED)
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    private fun sendEffect(effect: Effect) {
        _effect.trySend(effect)
    }

    fun sendEvent(event: ViewIntent) {
        val (newState, effect) = reducer.reduce(_state.value, event)
        _state.tryEmit(newState)
        effect?.let {
            sendEffect(it)
        }
    }
}