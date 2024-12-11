package com.fulbiopretell.retopichincha.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.data.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) : ViewModel() {

    private val _navigateToOnboarding = mutableStateOf(false)
    val navigateToOnboarding: Boolean get() = _navigateToOnboarding.value

    private val _navigateToHome = mutableStateOf(false)
    val navigateToHome: Boolean get() = _navigateToHome.value

    init {
        viewModelScope.launch {
            delay(2000)
            val shown = preferencesDataStore.isOnboardingShown()
            if (!shown) {
                _navigateToOnboarding.value = true
            } else {
                _navigateToHome.value = true
            }
        }
    }
}

