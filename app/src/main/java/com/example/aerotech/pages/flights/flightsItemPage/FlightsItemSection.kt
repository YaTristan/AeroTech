package com.example.aerotech.pages.flights.flightsItemPage

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.R
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.flights.flightsCreateSheet.CreateFlightsButton
import com.example.aerotech.pages.flights.flightsCreateSheet.DateFlights
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsData
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.MonitoringCreateSheet
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringViewModel
import com.example.aerotech.pages.monitoring.monitoringItemPage.BoxForBalance
import com.example.aerotech.pages.monitoring.monitoringItemPage.BoxForFuel
import com.example.aerotech.pages.monitoring.monitoringItemPage.BoxForInfoMonitoring
import com.example.aerotech.pages.monitoring.monitoringItemPage.EditDeleteMonitoring
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.GreenColor
import com.example.aerotech.ui.theme.TabFont
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightsItemSection(isVisible:Boolean, flightsId:Int, onDismiss: () -> Unit ) {
    val flightsViewModel: FlightsViewModel = viewModel()
    val flights = flightsViewModel.getFlightsById(flightsId).observeAsState()


    var name = remember { mutableStateOf("") }
    var date = remember { mutableStateOf("") }
    var sysAndComp = remember { mutableStateOf(true) }
    var elecAndAvi = remember { mutableStateOf(true) }
    var idenAndCert = remember { mutableStateOf(true) }
    var notes = remember { mutableStateOf("") }

    LaunchedEffect(flights.value) {
        flights.value?.let { flightsData ->
            name.value = flightsData.name ?: ""
            date.value = flightsData.date ?: ""
            sysAndComp.value = flightsData.sysAndComp
            elecAndAvi.value = flightsData.elecAndAvi
            idenAndCert.value = flightsData.idenAndCert
            notes.value = flightsData.notes ?: ""
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
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = name.value,
                                color = Color.White,
                                fontSize = 34.sp,
                                fontFamily = TabFont,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        BoxForDateFlights(date)
                        FlightsTrueFalse(title = "System and components", info = sysAndComp)
                        FlightsTrueFalse(title = "Electronic and avionics", info = elecAndAvi)
                        FlightsTrueFalse(
                            title = "Identification and certification",
                            info = idenAndCert
                        )
                        NotesBoxFlights(state = notes)
                        MakeReportButton(
                            title = "Make a report",
                            name = name.value,
                            date = date.value,
                            sysAndComp = sysAndComp.value,
                            elecAndAvi = elecAndAvi.value,
                            idenAndCert = idenAndCert.value,
                            notes = notes.value,
                            onCreate = {
                                flightsViewModel.updateFlights(
                                    FlightsData(
                                        id = flightsId,
                                        name = name.value,
                                        date = date.value,
                                        sysAndComp = sysAndComp.value,
                                        elecAndAvi = elecAndAvi.value,
                                        idenAndCert = idenAndCert.value,
                                        notes = notes.value,
                                        isReported = true,
                                    )
                                )
                            },
                            onDismiss = { onDismiss() })
                }
            }
        }
    }
}

@Composable
fun BoxForDateFlights(flightsDate: MutableState<String>){
    Column {
        Box(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = "Date of verification",
                color = Color.White,
                fontSize = 17.sp,
                fontFamily = TabFont,
                textAlign = TextAlign.Start,
            )
        }
        Row(
        ){
            Column(modifier = Modifier
                .padding(start = 8.dp, top = 16.dp)) {
                val context = LocalContext.current
                val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
                Box(
                    modifier = Modifier
                        .width(140.dp)
                        .height(45.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Background)
                        .border(width = 1.dp, color = Color.White, RoundedCornerShape(4.dp))
                        .clickable {
                            val calendar = Calendar.getInstance()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)

                            // Создаем DatePickerDialog
                            val datePickerDialog = DatePickerDialog(
                                context,
                                { _, selectedYear, selectedMonth, selectedDay ->
                                    // Обновляем календарь с выбранной датой
                                    calendar.set(selectedYear, selectedMonth, selectedDay)
                                    // Форматируем выбранную дату и обновляем состояние
                                    flightsDate.value = dateFormat.format(calendar.time)
                                },
                                year,
                                month,
                                day
                            )


                            // Показываем диалог выбора даты
                            datePickerDialog.show()
                        },
                    contentAlignment = Alignment.Center) {

                    Text(text = flightsDate.value,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = TabFont,
                        fontSize = 17.sp)
                }

            }
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Composable
fun FlightsTrueFalse(title:String, info:MutableState<Boolean>){
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(90.dp)
    ){
        Column {
            Text(
                text = title,
                fontSize = 17.sp,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier.padding(bottom = 16.dp, start = 8.dp)
            )
            Box(){
                Row {
                    Box(
                        modifier = Modifier
                            .alpha(if (info.value) 1f else 0.5f)
                            .clip(RoundedCornerShape(15.dp))
                            .padding(start = 8.dp, end = 8.dp)
                            .width(150.dp)
                            .height(120.dp)
                            .clickable { info.value = true }
                            .border(width = 1.dp, color = GreenColor, RoundedCornerShape(15.dp))
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
                            .alpha(if (info.value) 0.5f else 1f)
                            .clip(RoundedCornerShape(15.dp))
                            .padding(start = 8.dp, end = 8.dp)
                            .width(150.dp)
                            .height(120.dp)
                            .clickable { info.value = false }
                            .border(
                                width = 1.dp,
                                color = BottomBarBorder,
                                RoundedCornerShape(15.dp)
                            )

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

@Composable
fun NotesBoxFlights(state:MutableState<String>){
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(18.dp)),
        contentAlignment = Alignment.CenterStart
    ){
        Row {
            Text(
                text = "Notes",
                fontSize = 15.sp,
                fontFamily = TabFont,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            BasicTextField(
                value = state.value,
                onValueChange = { state.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = TabFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
                cursorBrush = SolidColor(Color.White),
                maxLines = 1,
            ) { innerTextField ->

                innerTextField()
            }
        }
    }
}

@Composable
fun MakeReportButton(
    title:String,
    name: String,
    date:String,
    sysAndComp:Boolean,
    elecAndAvi:Boolean,
    idenAndCert:Boolean,
    notes:String,
    onCreate: () -> Unit,
    onDismiss: () -> Unit){
    var alpha = name.isNullOrBlank()||date.isNullOrBlank()
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
                onCreate()
                coroutineScope.launch {
                    onDismiss() // Закрываем при создании
                }
            }
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(text = title,
            fontFamily = TabFont,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

