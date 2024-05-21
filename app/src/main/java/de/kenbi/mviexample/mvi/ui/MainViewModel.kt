package de.kenbi.mviexample.mvi.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.kenbi.mviexample.mvi.core.Intent
import de.kenbi.mviexample.mvi.core.IntentReceiver
import de.kenbi.mviexample.mvi.core.StateReducer
import de.kenbi.mviexample.mvi.ui.intents.MainIntent
import de.kenbi.mviexample.mvi.ui.state.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel(), IntentReceiver, StateReducer<MainIntent, MainState> {

    private val intentChannel = Channel<MainIntent>(Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.LoadingState)
        private set

    init {
        viewModelScope.launch {
            intentChannel.receiveAsFlow().collect { intent ->
                state.value = reduce(intent, state.value)
            }
        }
    }

    override fun handleIntent(intent: Intent) {
        viewModelScope.launch {
            //Can log intent
            val eventId = intent.getIntentIdentifier()
            if (intent is MainIntent)
                intentChannel.send(intent)
        }
    }

    override fun reduce(intent: MainIntent, state: MainState): MainState {
        return when (intent) {
            is MainIntent.LoginIntent -> {
                MainState.SuccessState(isLoggedIn = true)
            }

            is MainIntent.LogoutIntent -> {
                MainState.SuccessState(isLoggedIn = false)
            }
        }
    }

}