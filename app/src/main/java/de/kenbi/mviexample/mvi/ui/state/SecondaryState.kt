package de.kenbi.mviexample.mvi.ui.state

import de.kenbi.mviexample.mvi.core.ViewState

sealed class SecondaryState: ViewState {
    data object LoadingState : SecondaryState() {
        override val isLoading: Boolean = true
    }

    data object ErrorState : SecondaryState() {
        override val isLoading: Boolean = false
    }

    data object SuccessState : SecondaryState() {
        override val isLoading: Boolean = false
    }
}