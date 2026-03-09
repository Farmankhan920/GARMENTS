package com.farman.afgarments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farman.afgarments.data.SettingsManager
import com.farman.afgarments.data.models.*
import com.farman.afgarments.repository.GarmentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: GarmentRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    // Core Data States
    val entries = repository.allEntries.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val jacketTypes = repository.allJacketTypes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val workers = repository.allWorkers.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val expenses = repository.allExpenses.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Settings States
    val darkMode = settingsManager.darkModeFlow.stateIn(viewModelScope, SharingStarted.Lazily, true)
    val accentColor = settingsManager.accentFlow.stateIn(viewModelScope, SharingStarted.Lazily, "ROSE")
    val language = settingsManager.languageFlow.stateIn(viewModelScope, SharingStarted.Lazily, "en")
    val currency = settingsManager.currencyFlow.stateIn(viewModelScope, SharingStarted.Lazily, "₹")
    val soundEnabled = settingsManager.soundEnabledFlow.stateIn(viewModelScope, SharingStarted.Lazily, true)
    val vibrateEnabled = settingsManager.vibrateEnabledFlow.stateIn(viewModelScope, SharingStarted.Lazily, true)
    val confirmDelete = settingsManager.confirmDeleteFlow.stateIn(viewModelScope, SharingStarted.Lazily, true)
    val showRate = settingsManager.showRateFlow.stateIn(viewModelScope, SharingStarted.Lazily, true)
    val monthlyGoal = settingsManager.monthlyGoalFlow.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    // Entry Actions
    fun insertEntry(entry: Entry) = viewModelScope.launch { repository.insertEntry(entry) }
    fun updateEntry(entry: Entry) = viewModelScope.launch { repository.updateEntry(entry) }
    fun deleteEntry(entry: Entry) = viewModelScope.launch { repository.deleteEntry(entry) }

    // JacketType Actions
    fun insertJacketType(jacketType: JacketType) = viewModelScope.launch { repository.insertJacketType(jacketType) }
    fun updateJacketType(jacketType: JacketType) = viewModelScope.launch { repository.updateJacketType(jacketType) }
    fun deleteJacketType(jacketType: JacketType) = viewModelScope.launch { repository.deleteJacketType(jacketType) }

    // Worker Actions
    fun insertWorker(worker: Worker) = viewModelScope.launch { repository.insertWorker(worker) }
    fun updateWorker(worker: Worker) = viewModelScope.launch { repository.updateWorker(worker) }
    fun deleteWorker(worker: Worker) = viewModelScope.launch { repository.deleteWorker(worker) }

    // Expense Actions
    fun insertExpense(expense: StandaloneExpense) = viewModelScope.launch { repository.insertExpense(expense) }
    fun deleteExpense(expense: StandaloneExpense) = viewModelScope.launch { repository.deleteExpense(expense) }

    // Settings Actions
    fun setDarkMode(enabled: Boolean) = viewModelScope.launch { settingsManager.setDarkMode(enabled) }
    fun setAccentColor(accent: String) = viewModelScope.launch { settingsManager.setAccentColor(accent) }
    fun setLanguage(lang: String) = viewModelScope.launch { settingsManager.setLanguage(lang) }
    fun setCurrency(symbol: String) = viewModelScope.launch { settingsManager.setCurrency(symbol) }
    fun setSoundEnabled(enabled: Boolean) = viewModelScope.launch { settingsManager.setSoundEnabled(enabled) }
    fun setVibrateEnabled(enabled: Boolean) = viewModelScope.launch { settingsManager.setVibrateEnabled(enabled) }
    fun setConfirmDelete(enabled: Boolean) = viewModelScope.launch { settingsManager.setConfirmDelete(enabled) }
    fun setShowRate(enabled: Boolean) = viewModelScope.launch { settingsManager.setShowRate(enabled) }
    fun setMonthlyGoal(amount: Double) = viewModelScope.launch { settingsManager.setMonthlyGoal(amount) }
}
