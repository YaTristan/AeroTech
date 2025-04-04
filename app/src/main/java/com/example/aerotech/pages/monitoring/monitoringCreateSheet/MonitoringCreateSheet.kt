package com.example.aerotech.pages.monitoring.monitoringCreateSheet

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.hideNavigationBar
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.BoxForCreateAircraft
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringData
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.GreenColor
import com.example.aerotech.ui.theme.TabFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitoringCreateSheet(isVisible:Boolean,onDismiss: () -> Unit){
    val monitoringViewModel: MonitoringViewModel = viewModel()
    var name  = remember{ mutableStateOf("") }
    var weight  = remember{ mutableStateOf("") }
    var balance  = remember{ mutableStateOf(true) }
    var temperature = remember{ mutableStateOf("") }
    var pressure  = remember{ mutableStateOf("") }
    var consumption = remember { mutableStateOf("")}


    val suffixMap = mapOf(
        "Weight" to " kg",
        "Engine temperature" to " °C",
        "Air pressure" to " GPa",
        "Fuel consumption" to " g/pass-km"
    )


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
                    BoxForCreateMonitoring("Name", name, null, onAdd = {}) // Имя без добавления слова
                    BoxForCreateMonitoring("Weight", weight, suffixMap["Weight"], onAdd = {}) // Добавляем "kg"
                    BoxForCreateMonitoring("Engine temperature", temperature, suffixMap["Engine temperature"], onAdd = {}) // Добавляем "°C"
                    BalanceTrueFalse(balance)
                    Spacer(modifier = Modifier.padding(bottom = 15.dp))
                    BoxForCreateMonitoring("Air pressure", pressure, suffixMap["Air pressure"], onAdd = {}) // Добавляем "atm"
                    BoxForCreateMonitoring("Fuel consumption", consumption, suffixMap["Fuel consumption"], onAdd = {}) // Добавляем "L/100km"
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Spacer(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    MonitoringCreateButton(
                        title = "Add",
                        name = name,
                        weight = weight,
                        temperature = temperature,
                        pressure = pressure,
                        consumption = consumption,
                        showMonitoringSheet = showMonitoringSheet,
                        onCreate = {
                            monitoringViewModel.insertMonitoring(
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

@Composable
fun BoxForCreateMonitoring(title: String, state: MutableState<String>, suffix: String?, onAdd: () -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    // Local function to apply suffix
    fun applySuffixIfNeeded() {
        if (suffix != null && state.value.isNotEmpty() && !state.value.endsWith(suffix)) {
            state.value += suffix
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Background)
            .padding(bottom = 15.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(width = 1.dp, color = BottomBarBorder, RoundedCornerShape(18.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontFamily = TabFont,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
            )
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = state.value,
                    onValueChange = { newText ->
                        // Remove the suffix while typing to avoid duplication
                        if (suffix != null && newText.endsWith(suffix)) {
                            state.value = newText.removeSuffix(suffix)
                        } else {
                            state.value = newText
                        }
                    },
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isFocused.value = focusState.isFocused
                            if (!focusState.isFocused) {
                                applySuffixIfNeeded()
                            }
                        },
                    keyboardOptions = KeyboardOptions(
                        capitalization = if (title == "Serial number") KeyboardCapitalization.Characters else KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done,
                        keyboardType = if (title == "Name") KeyboardType.Text else KeyboardType.Number
                    ),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = TabFont,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    cursorBrush = SolidColor(Color.White),
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun BalanceTrueFalse(balance:MutableState<Boolean>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ){
        Column {
            Text(
                text = "Balance",
                fontSize = 15.sp,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier.padding(bottom = 16.dp, start = 8.dp)
            )
            Box(){
                Row {
                    Box(
                        modifier = Modifier
                            .alpha(if(balance.value) 1f else 0.5f)
                            .clip(RoundedCornerShape(15.dp))
                            .padding(start = 8.dp, end = 8.dp)
                            .width(150.dp)
                            .height(45.dp)
                            .clickable { balance.value= true }
                            .border(width = 1.dp, color =GreenColor,RoundedCornerShape(15.dp))
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Okay",
                            fontSize = 17.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = TabFont,
                            maxLines = 1,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .alpha(if(balance.value) 0.5f else 1f)
                            .clip(RoundedCornerShape(15.dp))
                            .padding(start = 8.dp, end = 8.dp)
                            .width(150.dp)
                            .height(45.dp)
                            .clickable { balance.value = false }
                            .border(width = 1.dp, color = BottomBarBorder,RoundedCornerShape(15.dp))

                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Violated",
                            fontSize = 17.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = TabFont,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}

fun applySuffixIfNeeded(value: MutableState<String>, suffix: String?) {
    if (suffix != null && value.value.isNotEmpty() && !value.value.endsWith(suffix)) {
        value.value += suffix
    }
}

@Composable
fun MonitoringCreateButton(
    title: String,
    name: MutableState<String>,
    weight: MutableState<String>,
    temperature: MutableState<String>,
    pressure: MutableState<String>,
    consumption: MutableState<String>,
    showMonitoringSheet: MutableState<Boolean>,
    onCreate: () -> Unit,
    onDismiss: () -> Unit,
    suffixMap: Map<String, String?> // Новый параметр для суффиксов
) {
    val alpha = name.value.isBlank() || weight.value.isBlank() ||
            temperature.value.isBlank() || pressure.value.isBlank() ||
            consumption.value.isBlank()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .alpha(if (alpha) 0.6f else 1f)
            .background(BottomBarBorder)
            .clickable {
                // Добавляем суффиксы перед созданием
                applySuffixIfNeeded(weight, suffixMap["Weight"])
                applySuffixIfNeeded(temperature, suffixMap["Engine temperature"])
                applySuffixIfNeeded(pressure, suffixMap["Air pressure"])
                applySuffixIfNeeded(consumption, suffixMap["Fuel consumption"])

                onCreate()
                coroutineScope.launch {
                    onDismiss() // Закрываем при создании
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontFamily = TabFont,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}