package com.fulbiopretell.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("preferences")

class PreferencesDataStore(private val context: Context) {
    companion object {
        val KEY_ONBOARDING_SHOWN = booleanPreferencesKey("onboarding_shown")
    }

    suspend fun isOnboardingShown(): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[KEY_ONBOARDING_SHOWN] ?: false
    }

    suspend fun setOnboardingShown(shown: Boolean) {
        context.dataStore.edit { it[KEY_ONBOARDING_SHOWN] = shown }
    }
}
