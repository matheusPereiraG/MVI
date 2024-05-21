package de.kenbi.mviexample.mvi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.kenbi.mviexample.mvi.core.IntentReceiver
import de.kenbi.mviexample.mvi.ui.intents.MainIntent
import de.kenbi.mviexample.mvi.ui.state.MainState

@Composable
fun MainScreen(
    uiState: MainState,
    intentHandler: IntentReceiver,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = { intentHandler.handleIntent(MainIntent.LoginIntent) }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { intentHandler.handleIntent(MainIntent.LogoutIntent) }) {
            Text(text = "Logout")
        }
        Text(text = "Im currently: $uiState")
    }
}