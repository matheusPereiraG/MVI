package de.kenbi.mviexample.mvi.ui.state

import de.kenbi.mviexample.mvi.core.ViewState

sealed class MainState : ViewState {
    data object LoadingState : MainState() {
        override val isLoading: Boolean = true
    }

    data object ErrorState : MainState() {
        override val isLoading: Boolean = false
    }

    data class SuccessState(val isLoggedIn: Boolean) : MainState() {
        override val isLoading: Boolean = false
    }
}