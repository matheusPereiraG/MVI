package de.kenbi.mviexample.mvi.multstate.statereducers

import de.kenbi.mviexample.mvi.core.StateReducer
import de.kenbi.mviexample.mvi.ui.intents.SecondaryIntent
import de.kenbi.mviexample.mvi.ui.state.SecondaryState
import javax.inject.Inject

class SecondaryStateReducer @Inject constructor() : StateReducer<SecondaryIntent, SecondaryState> {
    override fun reduce(intent: SecondaryIntent, state: SecondaryState): SecondaryState {
        return SecondaryState.LoadingState
    }
}