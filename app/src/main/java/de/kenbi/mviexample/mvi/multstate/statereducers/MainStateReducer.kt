package de.kenbi.mviexample.mvi.multstate.statereducers

import de.kenbi.mviexample.mvi.core.StateReducer
import de.kenbi.mviexample.mvi.ui.intents.MainIntent
import de.kenbi.mviexample.mvi.ui.state.MainState
import javax.inject.Inject

class MainStateReducer @Inject constructor() : StateReducer<MainIntent, MainState> {
    override fun reduce(intent: MainIntent, state: MainState): MainState {
        return when(intent) {
            is MainIntent.LoginIntent -> {
                MainState.SuccessState(isLoggedIn = true)
            }

            is MainIntent.LogoutIntent -> {
                MainState.SuccessState(isLoggedIn = false)
            }
        }
    }
}