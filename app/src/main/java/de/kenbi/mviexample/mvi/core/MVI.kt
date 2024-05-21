package de.kenbi.mviexample.mvi.core

interface ViewState {
    val isLoading: Boolean
}

fun interface Intent {
    fun getIntentIdentifier(): String
}