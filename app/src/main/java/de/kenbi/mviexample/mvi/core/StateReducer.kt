package de.kenbi.mviexample.mvi.core

fun interface StateReducer<in T : Intent, R : ViewState> {
    fun reduce(intent: T, state: R): R
}