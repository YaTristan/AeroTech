package com.example.aerotech.pages.analytics

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftCreateButton
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainSection
import com.example.aerotech.pages.analytics.analyticsSections.AnalyticsMainEmptySection
import com.example.aerotech.pages.analytics.analyticsSections.AnalyticsMainNonEmptySection
import com.example.aerotech.pages.analytics.analyticsSections.AnalyticsMainSection
import com.example.aerotech.pages.bars.BottomBar
import com.example.aerotech.pages.bars.TopBar
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.flights.flightsSections.FlightsCreateButton
import com.example.aerotech.pages.flights.flightsSections.FlightsMainEmptySection
import com.example.aerotech.pages.flights.flightsSections.FlightsMainNonEmptySection
import com.example.aerotech.pages.flights.flightsSections.FlightsMainSection
import com.example.aerotech.ui.theme.Background

@Composable
fun AnalyticsPage(navController: NavController){

    val viewModel: FlightsViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column {
            TopBar(title = "Analytics")
            Box(modifier = Modifier.weight(1f)) {
                AnalyticsMainSection(viewModel = viewModel)
            }
            Box{
                BottomBar(navController)
            }
        }
    }
}