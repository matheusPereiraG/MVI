package de.kenbi.mviexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import de.kenbi.mviexample.mvi.ui.MainScreen
import de.kenbi.mviexample.mvi.ui.MainViewModel
import de.kenbi.mviexample.ui.theme.MVIExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: MainViewModel by viewModels()
        setContent {
            MVIExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        uiState = viewModel.state.value,
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}