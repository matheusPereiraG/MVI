package de.kenbi.mviexample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import de.kenbi.mviexample.mvi.ui.intents.MainIntent
import de.kenbi.mviexample.mvi.ui.MainViewModel
import de.kenbi.mviexample.mvi.ui.state.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var subject: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun test_race_condition() = runTest  {
        subject.handleIntent(MainIntent.LoginIntent)
        subject.handleIntent(MainIntent.LogoutIntent)
        advanceUntilIdle()
        assertEquals(MainState.SuccessState(isLoggedIn = false), subject.state.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        subject = MainViewModel()
    }

    @After
    fun tearDown() {
    }
}