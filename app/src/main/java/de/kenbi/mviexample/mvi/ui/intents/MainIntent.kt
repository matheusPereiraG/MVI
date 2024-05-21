package de.kenbi.mviexample.mvi.ui.intents

import de.kenbi.mviexample.mvi.core.Intent

sealed class MainIntent : Intent {
    data object LoginIntent : MainIntent() {
        override fun getIntentIdentifier(): String {
            return "LOGIN_EVENT"
        }
    }

    data object LogoutIntent : MainIntent() {
        override fun getIntentIdentifier(): String {
            return "LOGOUT_EVENT"
        }
    }
}