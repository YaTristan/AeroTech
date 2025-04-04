package com.example.aerotech.pages.aircraft.aircraftItemPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.BoxForCreateAircraft
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.CreateButton
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.InspectionDate
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.TabFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAircraftItemSection(isVisible:Boolean,aircraftId:Int,onDismiss: () -> Unit){

    val aircraftViewModel: AircraftViewModel = viewModel()
    val aircraft = aircraftViewModel.getAircraftById(aircraftId).observeAsState()

    val name = remember { mutableStateOf("") }
    val model = remember { mutableStateOf("") }
    val serialNumber = remember { mutableStateOf("") }
    val lastInspection = remember { mutableStateOf("") }
    val upcomingInspection = remember { mutableStateOf("") }

    LaunchedEffect(aircraft.value) {
        aircraft.value?.let { aircraftData ->
            name.value = aircraftData.name ?: ""
            model.value = aircraftData.model ?: ""
            serialNumber.value = aircraftData.serial ?: ""
            lastInspection.value = aircraftData.lastInspection ?: ""
            upcomingInspection.value = aircraftData.upcomingInspection ?: ""
        }
    }


    var showAircraftSheet = remember { mutableStateOf(isVisible) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )


    if (isVisible) {
        ModalBottomSheet(
            containerColor = Background,
            sheetState = sheetState,
            onDismissRequest = { onDismiss() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)  // Применяем фон ко всему листу
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Edit",
                            textAlign = TextAlign.Center,
                            fontSize = 19.sp,
                            fontFamily = TabFont,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                    }
                    BoxForCreateAircraft("Name", name)
                    BoxForCreateAircraft("Model", model)
                    BoxForCreateAircraft("Serial number", serialNumber)
                    InspectionDate(lastInspection, upcomingInspection)
                    Spacer(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    CreateButton(
                        title = "Save",
                        name.value,
                        model.value,
                        serialNumber.value,
                        lastInspection.value,
                        upcomingInspection.value,
                        showAircraftSheet = showAircraftSheet,
                        onCreate = {
                            aircraftViewModel.updateAircraft(
                                AircraftData(
                                    id = aircraftId,
                                    name = name.value,
                                    model = model.value,
                                    serial = serialNumber.value,
                                    lastInspection = lastInspection.value,
                                    upcomingInspection = upcomingInspection.value,
                                )
                            )
                        },
                        onDismiss = { onDismiss() })
                }
            }
        }
    }
}