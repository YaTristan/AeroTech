package com.example.aerotech.pages.monitoring.monitoringSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftItemPage.AircraftItemPage
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringData
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.pages.monitoring.monitoringItemPage.MonitoringItemPage
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BackgroundCard
import com.example.aerotech.ui.theme.TabFont

@Preview
@Composable
fun MonitoringMainNonEmptySection(){
    val monitoringViewModel: MonitoringViewModel = viewModel()
    val monitoringList by monitoringViewModel.allMonitoring.observeAsState(emptyList())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        items(monitoringList) { monitoring ->
            MonitoringCard(monitoring)
        }
    }
}

@Composable
fun MonitoringCard(monitoring: MonitoringData){
    var isVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(start = 10.dp, end = 10.dp, top = 20.dp,)
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundCard)
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(16.dp))
            .clickable {
                isVisible = !isVisible
            }
    ){
        Row {
            Box(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = R.drawable.gear), contentDescription = null,)
            }
            Column(
                modifier = Modifier
                    .padding(start =12.dp,top =22.dp)
            ){
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = monitoring.name?:"",
                        color = Color.White,
                        fontFamily = TabFont,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ){
                Box(modifier = Modifier.padding(4.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                    )
                }
            }
        }
    }
    if(isVisible) {
        MonitoringItemPage(
            isVisible = isVisible,
            onDismiss= {isVisible = false},
            monitoringId = monitoring.id
        )
    }
}