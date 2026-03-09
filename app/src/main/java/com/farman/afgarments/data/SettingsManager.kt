package com.farman.afgarments.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "af_settings")

class SettingsManager(private val context: Context) {

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val ACCENT_COLOR = stringPreferencesKey("accent")
        val LANGUAGE = stringPreferencesKey("language")
        val CURRENCY = stringPreferencesKey("currency")
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val VIBRATE_ENABLED = booleanPreferencesKey("vibrate_enabled")
        val CONFIRM_DELETE = booleanPreferencesKey("confirm_delete")
        val SHOW_RATE = booleanPreferencesKey("show_rate")
        val MONTHLY_GOAL = doublePreferencesKey("monthly_goal")
    }

    val darkModeFlow: Flow<Boolean> = context.dataStore.data.map { it[DARK_MODE] ?: true }
    val accentFlow: Flow<String> = context.dataStore.data.map { it[ACCENT_COLOR] ?: "ROSE" }
    val languageFlow: Flow<String> = context.dataStore.data.map { it[LANGUAGE] ?: "en" }
    val currencyFlow: Flow<String> = context.dataStore.data.map { it[CURRENCY] ?: "₹" }
    val soundEnabledFlow: Flow<Boolean> = context.dataStore.data.map { it[SOUND_ENABLED] ?: true }
    val vibrateEnabledFlow: Flow<Boolean> = context.dataStore.data.map { it[VIBRATE_ENABLED] ?: true }
    val confirmDeleteFlow: Flow<Boolean> = context.dataStore.data.map { it[CONFIRM_DELETE] ?: true }
    val showRateFlow: Flow<Boolean> = context.dataStore.data.map { it[SHOW_RATE] ?: true }
    val monthlyGoalFlow: Flow<Double> = context.dataStore.data.map { it[MONTHLY_GOAL] ?: 0.0 }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { it[DARK_MODE] = enabled }
    }

    suspend fun setAccentColor(accent: String) {
        context.dataStore.edit { it[ACCENT_COLOR] = accent }
    }

    suspend fun setLanguage(lang: String) {
        context.dataStore.edit { it[LANGUAGE] = lang }
    }

    suspend fun setCurrency(symbol: String) {
        context.dataStore.edit { it[CURRENCY] = symbol }
    }

    suspend fun setSoundEnabled(enabled: Boolean) {
        context.dataStore.edit { it[SOUND_ENABLED] = enabled }
    }

    suspend fun setVibrateEnabled(enabled: Boolean) {
        context.dataStore.edit { it[VIBRATE_ENABLED] = enabled }
    }

    suspend fun setConfirmDelete(enabled: Boolean) {
        context.dataStore.edit { it[CONFIRM_DELETE] = enabled }
    }

    suspend fun setShowRate(enabled: Boolean) {
        context.dataStore.edit { it[SHOW_RATE] = enabled }
    }

    suspend fun setMonthlyGoal(goal: Double) {
        context.dataStore.edit { it[MONTHLY_GOAL] = goal }
    }
}
