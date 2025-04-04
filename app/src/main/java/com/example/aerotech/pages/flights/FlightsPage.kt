package com.example.aerotech.pages.flights

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftCreateButton
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainSection
import com.example.aerotech.pages.bars.BottomBar
import com.example.aerotech.pages.bars.TopBar
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.flights.flightsSections.FlightsCreateButton
import com.example.aerotech.pages.flights.flightsSections.FlightsMainEmptySection
import com.example.aerotech.pages.flights.flightsSections.FlightsMainSection
import com.example.aerotech.ui.theme.Background

@Composable
fun FlightsPage(navController: NavController){

    val viewModel: FlightsViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column {
            TopBar(title = "Flights")
            Box(modifier = Modifier.weight(1f)) {
                FlightsMainSection(viewModel = viewModel)
            }
            FlightsCreateButton()
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            Box{
                BottomBar(navController)
            }
        }
    }
}