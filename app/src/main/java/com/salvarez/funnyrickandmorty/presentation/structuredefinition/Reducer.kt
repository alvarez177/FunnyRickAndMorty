package com.salvarez.funnyrickandmorty.presentation.structuredefinition

interface Reducer<State: Reducer.ViewState, Event: Reducer.ViewIntent, Effect: Reducer.ViewEffect> {

    interface ViewState

    interface ViewIntent

    interface ViewEffect

    fun reduce(previousState: State, intent: Event): Pair<State, Effect?>
}