package com.example.aerotech.pages.aircraft.aircraftSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BackgroundCard
import com.example.aerotech.ui.theme.TabFont

@Preview
@Composable
fun AircraftMainNonEmptySection(){
    val aircraftViewModel: AircraftViewModel = viewModel()
    val aircraftList by aircraftViewModel.allAircrafts.observeAsState(emptyList())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        items(aircraftList) { aircraft ->
            AircraftCard(aircraft) // Отображаем карточку для каждого самолета
        }
    }
}

@Composable
fun AircraftCard(aircraft:AircraftData){
    var isVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(start = 10.dp, end = 10.dp, top = 20.dp,)
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundCard)
            .clickable {
                isVisible = !isVisible
            }
    ){
        Row {
            Box(modifier = Modifier
                .width(148.dp)
                .padding(start = 8.dp, bottom = 12.dp, top = 8.dp)
                .fillMaxHeight(), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.plane), contentDescription = null)
            }
            Column(
                modifier = Modifier
                    .padding(start =12.dp,top =22.dp)
            ){
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = aircraft.name?:"",
                        color = Color.White,
                        fontFamily = TabFont,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                }
                Box(
                    modifier = Modifier.padding(top=0.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = aircraft.model?:"",
                        color = Color.Gray,
                        fontFamily = TabFont,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
    if(isVisible)
        AircraftItemPage(
            isVisible = isVisible,
            onDismiss= {isVisible = false},
            aircraftId = aircraft.id
        )
}