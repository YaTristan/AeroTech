package com.example.aerotech.pages.aircraft.aircraftSections

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.TabFont


@Composable
fun AircraftMainSection(viewModel: AircraftViewModel) {
    // Подсчитываем количество самолетов из ViewModel
    val itemCount by viewModel.aircraftList.collectAsState(initial = emptyList())
    val hasAircrafts = itemCount.isNotEmpty()

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
            if (hasAircrafts) {
                Spacer(modifier = Modifier.weight(1f))
                AircraftMainNonEmptySection()
            } else {
                AircraftMainEmptySection()
            }
        }
    }
}