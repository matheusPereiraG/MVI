package de.kenbi.mviexample.mvi.multstate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.kenbi.mviexample.mvi.core.Intent
import de.kenbi.mviexample.mvi.core.IntentReceiver
import de.kenbi.mviexample.mvi.ui.intents.MainIntent
import de.kenbi.mviexample.mvi.ui.state.MainState
import de.kenbi.mviexample.mvi.ui.intents.SecondaryIntent
import de.kenbi.mviexample.mvi.ui.state.SecondaryState
import de.kenbi.mviexample.mvi.multstate.statereducers.MainStateReducer
import de.kenbi.mviexample.mvi.multstate.statereducers.SecondaryStateReducer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompositionViewModel @Inject constructor(
    private val mainStateReducer: MainStateReducer,
    private val secondaryStateReducer: SecondaryStateReducer,
) : ViewModel(), IntentReceiver {

    private val intentChannel = Channel<Intent>(Channel.UNLIMITED)
    var primaryState = mutableStateOf<MainState>(MainState.LoadingState)
        private set

    var secondaryState = mutableStateOf<SecondaryState>(SecondaryState.LoadingState)
        private set

    init {
        viewModelScope.launch {
            intentChannel.receiveAsFlow().collect { intent ->
                when (intent) {
                    is MainIntent -> {
                        primaryState.value = mainStateReducer.reduce(intent, primaryState.value)
                    }

                    is SecondaryIntent -> {
                        secondaryState.value =
                            secondaryStateReducer.reduce(intent, secondaryState.value)
                    }
                }
            }
        }
    }

    override fun handleIntent(intent: Intent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }
}