package com.example.aerotech.pages.monitoring.monitoringSections

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainEmptySection
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainNonEmptySection
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.ui.theme.Background

@Composable
fun MonitoringMainSection(viewModel: MonitoringViewModel) {
    // Подсчитываем количество самолетов из ViewModel
    val itemCount by viewModel.monitoringList.collectAsState(initial = emptyList())
    val hasMonitoring = itemCount.isNotEmpty()

    Log.d("AircraftMainSection", "itemCount: ${itemCount.size}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (hasMonitoring) {
                Spacer(modifier = Modifier.weight(1f))
                MonitoringMainNonEmptySection()
            } else {
                MonitoringMainEmptySection()
            }
        }
    }
}