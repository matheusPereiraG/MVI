package de.kenbi.mviexample.mvi.ui.intents

import de.kenbi.mviexample.mvi.core.Intent

sealed class SecondaryIntent: Intent {
    data class LoadDataIntent(val data: String) : SecondaryIntent() {
        override fun getIntentIdentifier(): String {
            return "LOAD_DATA_EVENT"
        }
    }

    data object ClearDataIntent : SecondaryIntent() {
        override fun getIntentIdentifier(): String {
            return "CLEAR_DATA_EVENT"
        }
    }
}