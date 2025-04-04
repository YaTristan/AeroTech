package com.example.aerotech.pages.flights.flightsSections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.AircraftCreateSheet
import com.example.aerotech.pages.flights.flightsCreateSheet.FlightsCreateSheet
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.TabFont

@Preview
@Composable
fun FlightsCreateButton(){
    var isVisible by remember{ mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                isVisible = !isVisible
            }
            .background(BottomBarBorder),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Add nearest flights",
            color = Color.White,
            fontFamily = TabFont,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold)
    }

    if(isVisible)
        FlightsCreateSheet(isVisible = isVisible, onDismiss = { isVisible = false })
}