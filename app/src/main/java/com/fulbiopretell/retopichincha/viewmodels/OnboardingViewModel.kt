package com.fulbiopretell.retopichincha.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.data.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) : ViewModel() {
    fun completeOnboarding() {
        viewModelScope.launch {
            preferencesDataStore.setOnboardingShown(true)
        }
    }
}
