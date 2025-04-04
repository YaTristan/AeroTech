package com.example.aerotech.pages.aircraft.aircraftItemPage

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
import androidx.compose.material.icons.rounded.Airlines
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BackgroundCard
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.TabFont
import com.example.aerotech.ui.theme.TopBarBack


@Composable
fun AircraftItemSection(aircraftId:Int, isVisible:Boolean, onDismiss: () -> Unit){
    val aircraftViewModel: AircraftViewModel = viewModel()
    val aircraft = aircraftViewModel.getAircraftById(aircraftId).observeAsState()

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
            aircraft.value?.let { aircraftData ->
                EditDelete(aircraft = aircraftData, aircraftId = aircraftId, onDismiss = onDismiss)
            }
        }
        aircraft.value?.let {
            Column(
                Modifier.padding(top = 4.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.name ?: "Airbus 3000",
                        color = Color.White,
                        fontSize = 34.sp,
                        fontFamily = TabFont,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                Box(modifier = Modifier
                    .padding(start = 32.dp,end = 32.dp),
                    contentAlignment = Alignment.Center
                ){
                Image(painter = painterResource(id = R.drawable.plane_to_top), contentDescription = null)
                }
                Divider(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
                        .height(1.dp),
                    color = BottomBarBorder
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                Row(modifier = Modifier.padding(start = 8.dp)) {
                    Column {
                        BoxForInfo(title = "Model", info = it.model ?: "")
                        BoxForInfo(title = "Last inspection", info = it.lastInspection ?: "")
                    }
                    Column {
                        BoxForInfo(title = "Serial number", info = it.serial ?: "")
                        BoxForInfo(
                            title = "Upcoming inspection",
                            info = it.upcomingInspection ?: ""
                        )
                    }
                }
                }
            }
        }
    }
}

@Composable
fun BoxForInfo(title:String, info:String){
    Box(
        modifier = Modifier
            .padding(top = 16.dp, end = 8.dp)
            .width(184.dp)
            .height(75.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp))
            .background(BackgroundCard),
        contentAlignment = Alignment.Center
    ){
        Row{
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp, start = 12.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = R.drawable.ellips), contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 12.dp, end = 2.dp)
            ){
                Column {
                    Text(
                        text = title,
                        color = Color.Gray,
                        fontSize = 11.sp,
                        fontFamily = TabFont,
                        maxLines = 1
                    )
                    Text(
                        text = info,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontFamily = TabFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun EditDelete(aircraft: AircraftData, aircraftId:Int, onDismiss: () -> Unit){
    val aircraftViewModel: AircraftViewModel = viewModel()
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
                aircraftViewModel.deleteAircraft(aircraft)
                showDialog.value = false
                onDismiss()
            },
            onDismiss = { showDialog.value = false }
        )
    }
    if(editSheetState.value) {
        EditAircraftItemSection(
            isVisible = editSheetState.value,
            aircraftId = aircraftId,
            onDismiss = {
                editSheetState.value = false
                onDismiss()
            }
        )
    }
}