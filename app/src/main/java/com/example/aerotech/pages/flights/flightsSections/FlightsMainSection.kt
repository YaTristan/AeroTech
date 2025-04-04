package com.example.aerotech.pages.flights.flightsSections

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.monitoring.monitoringSections.MonitoringMainEmptySection
import com.example.aerotech.pages.monitoring.monitoringSections.MonitoringMainNonEmptySection
import com.example.aerotech.ui.theme.Background

@Composable
fun FlightsMainSection(viewModel:FlightsViewModel){
    // Подсчитываем количество неотчетных рейсов из ViewModel
    val unreportedFlightsCount by viewModel.unreportedFlightsCount.observeAsState(0)

    val hasUnreportedFlights = remember(unreportedFlightsCount) { unreportedFlightsCount > 0 }


        // Этот блок будет выполнен при изменении hasUnreportedFlights
        Log.d("FlightsMainSection", "hasUnreportedFlights изменился: $hasUnreportedFlights")
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
            if (hasUnreportedFlights) {
                Spacer(modifier = Modifier.weight(1f))
                FlightsMainNonEmptySection()
            } else {
                FlightsMainEmptySection()
            }
        }
    }
}