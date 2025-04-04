package com.example.aerotech.pages.monitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftCreateButton
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainEmptySection
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainSection
import com.example.aerotech.pages.bars.BottomBar
import com.example.aerotech.pages.bars.TopBar
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.pages.monitoring.monitoringSections.MonitoringCreateButton
import com.example.aerotech.pages.monitoring.monitoringSections.MonitoringMainEmptySection
import com.example.aerotech.pages.monitoring.monitoringSections.MonitoringMainNonEmptySection
import com.example.aerotech.pages.monitoring.monitoringSections.MonitoringMainSection
import com.example.aerotech.ui.theme.Background

@Composable
fun MonitoringPage(navController: NavController){

    val viewModel: MonitoringViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column {
            TopBar(title = "Monitoring")
            Box(modifier = Modifier.weight(1f)) {
                MonitoringMainSection(viewModel = viewModel)
            }
            MonitoringCreateButton()
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            Box{
                BottomBar(navController)
            }
        }
    }
}