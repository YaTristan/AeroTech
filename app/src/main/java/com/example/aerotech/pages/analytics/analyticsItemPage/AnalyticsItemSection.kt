package com.example.aerotech.pages.analytics.analyticsItemPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftItemPage.DeleteAircraftItemDialog
import com.example.aerotech.pages.aircraft.aircraftItemPage.EditAircraftItemSection
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsData
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.monitoring.monitoringItemPage.EditDeleteMonitoring
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.GreenColor
import com.example.aerotech.ui.theme.TabFont
import com.example.aerotech.ui.theme.TopBarBack

@Composable
fun AnalyticsItemSection(isVisible:Boolean, flightsId:Int, onDismiss: () -> Unit ){
    val flightsViewModel: FlightsViewModel = viewModel()
    val flights = flightsViewModel.getFlightsById(flightsId).observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            // Pass the aircraft object here
            flights.value?.let { flightsData ->
                DeleteAnalytics(flights = flightsData, flightsId = flightsId) {

                }
            }
        }
        flights.value?.let {
            Column(
                Modifier.padding(top = 4.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    )
                        {
                        Box(
                            modifier = Modifier.width(354.dp)
                            , contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it.name ?: "",
                                color = Color.White,
                                fontSize = 34.sp,
                                fontFamily = TabFont,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                            Spacer(modifier = Modifier.padding(bottom = 24.dp))

                        Row {
                            BoxForDate(title = "Date of verification", info = it.date)
                            BoxForBoolean(title = "System and components", info = it.sysAndComp)
                        }
                        Row {
                            BoxForBoolean(title = "Electronics and avionics", info = it.elecAndAvi)
                            BoxForBoolean(
                                title = "Identification and certification",
                                info = it.idenAndCert
                            )
                        }
                        BoxForNotes(title = "Notes", info = it.notes)
                    }
                }
            }
        }
    }
}

@Composable
fun BoxForDate(title:String, info:String){
    Box(
        modifier= Modifier
            .padding(end = 8.dp, bottom = 12.dp)
            .width(172.dp)
            .height(136.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
    ) {
        Column(
        ) {
            Spacer(modifier = Modifier.padding(top =16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = R.drawable.ellips), contentDescription = null)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ){
                Column {
                    Text(
                        text = title,
                        fontSize = 11.sp,
                        fontFamily = TabFont,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = info,
                        fontSize = 22.sp,
                        fontFamily = TabFont,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BoxForNotes(title:String, info:String){
    Box(
        modifier= Modifier
            .padding(bottom = 12.dp)
            .width(354.dp)
            .height(122.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
    ) {
        Column(
        ) {
            Spacer(modifier = Modifier.padding(top =16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = R.drawable.ellips), contentDescription = null)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ){
                Column {
                    Text(
                        text = title,
                        fontSize = 11.sp,
                        fontFamily = TabFont,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = info,
                        fontSize = 22.sp,
                        fontFamily = TabFont,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BoxForBoolean(title:String, info:Boolean){
    Box(
        modifier= Modifier
            .padding(end = 8.dp, bottom = 12.dp)
            .width(172.dp)
            .height(136.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
    ) {
        Column(
        ) {
            Spacer(modifier = Modifier.padding(top =16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = R.drawable.ellips), contentDescription = null)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ){
                Column {
                    Text(
                        text = title,
                        fontSize = 11.sp,
                        fontFamily = TabFont,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = if(info)"Okay" else "Violated",
                        fontSize = 22.sp,
                        fontFamily = TabFont,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = if(info) GreenColor else BottomBarBorder
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteAnalytics(flights: FlightsData, flightsId:Int, onDismiss: () -> Unit){
        val flightsViewModel: FlightsViewModel = viewModel()
        val showDialog = remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .clickable {
                        showDialog.value = true
                    }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete",
                    tint = TopBarBack,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                )
            }
        if(showDialog.value) {
            DeleteAnalyticsDialog(
                onConfirm = {
                    flightsViewModel.deleteFlights(flights)
                    showDialog.value = false
                    onDismiss()
                },
                onDismiss = { showDialog.value = false }
            )
        }
}

@Composable
fun DeleteAnalyticsDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .width(270.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Background) // Установка фона диалога
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Deletion", color = Color.White, fontSize = 17.sp, fontFamily = TabFont, modifier = Modifier.padding(top =16.dp))
                Text(text = "Do you really want to delete it?", color = Color.White,fontSize = 13.sp, fontFamily = TabFont, modifier = Modifier.padding(top = 8.dp))
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.padding(end = 48.dp)) {
                        TextButton(onClick = onDismiss) {
                            Text(text = "No", color = Color.White)
                        }
                    }
                    Box() {
                        TextButton(onClick = onConfirm) {
                            Text(text = "Yes", color = TopBarBack)
                        }
                    }
                }
            }
        }
    }
}