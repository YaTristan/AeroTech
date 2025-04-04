package com.example.aerotech.pages.monitoring.monitoringItemPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.BoxForCreateAircraft
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.CreateButton
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.InspectionDate
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.BalanceTrueFalse
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.BoxForCreateMonitoring
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.MonitoringCreateButton
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringData
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.TabFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMonitoringItemSection(isVisible:Boolean,monitoringId:Int,onDismiss: () -> Unit){

    val monitoringViewModel: MonitoringViewModel = viewModel()
    val monitoring = monitoringViewModel.getMonitoringById(monitoringId).observeAsState()


    val suffixMap = mapOf(
        "Weight" to " kg",
        "Engine temperature" to " °C",
        "Air pressure" to " GPa",
        "Fuel consumption" to " g/pass-km"
    )

    val name = remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }
    val temperature = remember { mutableStateOf("") }
    val balance = remember { mutableStateOf(true) }
    val pressure = remember { mutableStateOf("") }
    val consumption = remember { mutableStateOf("") }

    LaunchedEffect(monitoring.value) {
        monitoring.value?.let { monitoringData ->
            name.value = monitoringData.name ?: ""
            weight.value = monitoringData.weight ?: ""
            temperature.value = monitoringData.temperature ?: ""
            balance.value = monitoringData.balance ?: true
            pressure.value = monitoringData.pressure ?: ""
            consumption.value = monitoringData.consumption ?: ""
        }
    }


    var showMonitoringSheet = remember { mutableStateOf(isVisible) }
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
                    .background(Background)
                    .padding(16.dp)
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState) // Добавляем вертикальную прокрутку
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "New monitoring of parameters",
                            textAlign = TextAlign.Center,
                            fontSize = 19.sp,
                            fontFamily = TabFont,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                    }
                    BoxForCreateMonitoring("Name", name,null, onAdd = {}) // Имя без добавления слова
                    BoxForCreateMonitoring("Weight", weight,null, onAdd = {}) // Добавляем "kg"
                    BoxForCreateMonitoring("Engine temperature", temperature,null, onAdd = {}) // Добавляем "°C"
                    BalanceTrueFalse(balance)
                    Spacer(modifier = Modifier.padding(bottom = 15.dp))
                    BoxForCreateMonitoring("Air pressure", pressure,null, onAdd = {}) // Добавляем "atm"
                    BoxForCreateMonitoring("Fuel consumption", consumption,null, onAdd = {}) // Добавляем "L/100km"
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Spacer(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    MonitoringCreateButton(
                        title = "Save",
                        name = name,
                        weight = weight,
                        temperature = temperature,
                        pressure = pressure,
                        consumption = consumption,
                        showMonitoringSheet = showMonitoringSheet,
                        onCreate = {
                            monitoringViewModel.updateMonitoring(
                                MonitoringData(
                                    name = name.value,
                                    weight = weight.value,
                                    temperature = temperature.value,
                                    balance = balance.value,
                                    pressure = pressure.value,
                                    consumption = consumption.value,
                                )
                            )
                        },
                        onDismiss = { onDismiss() },
                        suffixMap = suffixMap)
                }
            }
        }
    }
}