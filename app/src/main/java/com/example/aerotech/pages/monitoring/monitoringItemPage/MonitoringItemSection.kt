package com.example.aerotech.pages.monitoring.monitoringItemPage

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.aircraft.aircraftItemPage.BoxForInfo
import com.example.aerotech.pages.aircraft.aircraftItemPage.DeleteAircraftItemDialog
import com.example.aerotech.pages.aircraft.aircraftItemPage.EditAircraftItemSection
import com.example.aerotech.pages.aircraft.aircraftItemPage.EditDelete
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringData
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BackgroundCard
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.GreenColor
import com.example.aerotech.ui.theme.TabFont
import com.example.aerotech.ui.theme.TopBarBack

@Composable
fun MonitoringItemSection(isVisible:Boolean, monitoringId:Int, onDismiss: () -> Unit ){
    val monitoringViewModel: MonitoringViewModel = viewModel()
    val monitoring = monitoringViewModel.getMonitoringById(monitoringId).observeAsState()

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
            monitoring.value?.let { monitoringData ->
                EditDeleteMonitoring(monitoring = monitoringData, monitoringId = monitoringId, onDismiss = onDismiss)
            }
        }
        monitoring.value?.let {
            Column(
                Modifier.padding(top = 4.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
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
                Spacer(modifier = Modifier.padding(top =24.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Row(modifier = Modifier.padding(start = 8.dp)) {
                        Column {
                            BoxForInfoMonitoring(title = "Weight", info = it.weight, icon = R.drawable.weigh)
                            BoxForInfoMonitoring(title = "Engine temperature", info = it.temperature, icon = R.drawable.therm)
                        }
                        Column {
                            BoxForBalance(title = "Balance", info = it.balance, icon = R.drawable.airplane)
                            BoxForInfoMonitoring(title = "Air pressure", info = it.pressure, icon = R.drawable.wind)
                        }
                    }
                }
                BoxForFuel(title = "Fuel consumption", info = it.consumption, icon = R.drawable.fuel)
            }
        }
    }
}

@Composable
fun BoxForInfoMonitoring(title:String, info:String, icon:Int){
    Box(
        modifier= Modifier
            .padding(end = 8.dp, bottom = 16.dp)
            .width(172.dp)
            .height(122.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(top =16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = icon), contentDescription = null)
            }
            Box(
                modifier = Modifier.fillMaxWidth()
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
fun BoxForBalance(title:String, info:Boolean, icon:Int){
    Box(
        modifier= Modifier
            .padding(end = 8.dp, bottom = 16.dp)
            .width(172.dp)
            .height(122.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(top =16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = icon), contentDescription = null)
            }
            Box(
                modifier = Modifier.fillMaxWidth()
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
fun BoxForFuel(title:String, info:String, icon:Int){
    Box(
        modifier= Modifier
            .padding(start = 25.4.dp, end = 29.3.dp, bottom = 24.dp)
            .fillMaxWidth()
            .height(122.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(top =16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = icon), contentDescription = null)
            }
            Box(
                modifier = Modifier.fillMaxWidth()
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
fun EditDeleteMonitoring(monitoring: MonitoringData, monitoringId:Int, onDismiss: () -> Unit){
    val monitoringViewModel: MonitoringViewModel = viewModel()
    val showDialog = remember { mutableStateOf(false) }
    val editSheetState = remember { mutableStateOf(false) }
    Row(){
        Box(
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
                .clickable {
                    editSheetState.value = true
                }
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = null,
                tint = TopBarBack,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize()
            )
        }
        Spacer(
            modifier = Modifier.padding(start = 6.dp, end = 6.dp)
        )
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
    }
    if(showDialog.value) {
        DeleteAircraftItemDialog(
            onConfirm = {
                monitoringViewModel.deleteMonitoring(monitoring)
                showDialog.value = false
                onDismiss()
            },
            onDismiss = { showDialog.value = false }
        )
    }
    if(editSheetState.value) {
        EditMonitoringItemSection(
            isVisible = editSheetState.value,
            monitoringId = monitoringId,
            onDismiss = {
                editSheetState.value = false
                onDismiss()
            }
        )
    }
}
