package com.example.aerotech.pages.aircraft

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftCreateButton
import com.example.aerotech.pages.aircraft.aircraftSections.AircraftMainSection
import com.example.aerotech.pages.bars.BottomBar
import com.example.aerotech.pages.bars.TopBar
import com.example.aerotech.ui.theme.Background

@Composable
fun AircraftPage(navController: NavController){

    val viewModel: AircraftViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column {
            TopBar(title = "Maintenance")
            Box(modifier = Modifier.weight(1f)) {
                AircraftMainSection(viewModel = viewModel)
            }
            AircraftCreateButton()
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            Box{
                BottomBar(navController)
            }
        }
    }
}