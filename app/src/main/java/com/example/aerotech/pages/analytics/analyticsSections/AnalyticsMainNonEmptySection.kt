package com.example.aerotech.pages.analytics.analyticsSections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.pages.analytics.analyticsItemPage.AnalyticsItemPage
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsData
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.flights.flightsItemPage.FlightsItemPage
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BackgroundCard
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.TabFont

@Preview
@Composable
fun AnalyticsMainNonEmptySection(){
    val flightsViewModel: FlightsViewModel = viewModel()
    val flightsList by flightsViewModel.allReported.observeAsState(emptyList())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        items(flightsList) { flights ->
            FlightsCard(flights)
        }
    }
}

@Composable
fun FlightsCard(flights: FlightsData){
    var isVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(start = 10.dp, end = 10.dp, top =20.dp,)
            .clip(RoundedCornerShape(16.dp))
            .clickable { isVisible = true }
            .background(BackgroundCard)
    ){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row{
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start =12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = flights.name,
                            color = Color.White,
                            fontFamily = TabFont,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end =12.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "Report from the ${flights.date}",
                            color = Color.Gray,
                            fontFamily = TabFont,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
            }
        }
    }
    if(isVisible) {
        AnalyticsItemPage(
            isVisible = isVisible,
            onDismiss= {isVisible = false},
            flightsId = flights.id
        )
    }
}