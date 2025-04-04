package com.example.aerotech.pages.flights.flightsSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.aerotech.R
import com.example.aerotech.ui.theme.TabFont

@Preview
@Composable
fun FlightsMainEmptySection(){
    Column {
        Image(painter = painterResource(id = R.drawable.ticket), contentDescription = "",
            modifier = Modifier.fillMaxWidth())
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            Text(
                text = "Nearest flights",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = TabFont,
            )
        }
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(
                text = "Fill out the nearest flights to",
                fontSize = 15.sp,
                color = Color.Gray,
                fontFamily = TabFont
            )
        }
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(
                text = "check the aircraft",
                fontSize = 15.sp,
                color = Color.Gray,
                fontFamily = TabFont
            )
        }
    }
}