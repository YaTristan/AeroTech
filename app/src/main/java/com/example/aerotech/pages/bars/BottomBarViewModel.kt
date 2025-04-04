package com.example.aerotech.pages.bars

import androidx.lifecycle.ViewModel
import com.example.aerotech.pages.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BottomBarViewModel : ViewModel() {
    private val _selectedId = MutableStateFlow(0) // Начальное значение
    val selectedId: StateFlow<Int> = _selectedId

    fun updateSelectedId(newId: Int) {
        _selectedId.value = newId
    }

    fun initializeSelectedId(route: String) {
        // Установите выбранный ID на основе маршрута
        _selectedId.value = when (route) {
            Routes.aircraftPage -> 0
            Routes.monitoringPage -> 1
            Routes.flightsPage -> 2
            Routes.analyticsPage -> 3
            Routes.settingsPage -> 4
            else -> 0 // По умолчанию
        }
    }
}