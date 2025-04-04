package com.example.aerotech.pages.analytics.analyticsSections

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainEmptySection
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainNonEmptySection
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.ui.theme.Background

@Composable
fun AnalyticsMainSection(viewModel: FlightsViewModel){
    // Подсчитываем количество неотчетных рейсов из ViewModel
    val reportedFlightsCount by viewModel.reportedFlightsCount.observeAsState(0)

    val hasReportedFlights = remember(reportedFlightsCount) { reportedFlightsCount > 0 }


    // Этот блок будет выполнен при изменении hasUnreportedFlights
    Log.d("FlightsMainSection", "hasUnreportedFlights изменился: $hasReportedFlights")
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
            if (hasReportedFlights) {
                Spacer(modifier = Modifier.weight(1f))
                AnalyticsMainNonEmptySection()
            } else {
                AnalyticsMainEmptySection()
            }
        }
    }
}