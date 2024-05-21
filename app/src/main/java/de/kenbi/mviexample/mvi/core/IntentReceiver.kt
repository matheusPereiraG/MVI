package de.kenbi.mviexample.mvi.core

fun interface IntentReceiver {
    fun handleIntent(intent: Intent)
}