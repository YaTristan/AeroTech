package com.example.aerotech.pages.flights.flightsCreateSheet

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.CreateButton
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsData
import com.example.aerotech.pages.flights.flightsCreateSheet.data.FlightsViewModel
import com.example.aerotech.pages.monitoring.monitoringCreateSheet.data.MonitoringData
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.TabFont
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightsCreateSheet(isVisible:Boolean,onDismiss: () -> Unit) {

    val flightsViewModel: FlightsViewModel = viewModel()

    var name  = remember{ mutableStateOf("") }
    var date  = remember{ mutableStateOf("") }

    var showFlightsSheet = remember { mutableStateOf(isVisible) }
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
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "New nearest flights",
                            textAlign = TextAlign.Center,
                            fontSize = 19.sp,
                            fontFamily = TabFont,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                    }

                    BoxForCreateFlights(title = "Name", state = name)
                    DateFlights(date)
                    Spacer(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    CreateFlightsButton(
                        title = "Add",
                        name = name.value,
                        date = date.value,
                        showFlightsSheet = showFlightsSheet,
                        onCreate = {
                            flightsViewModel.insertFlights(
                                FlightsData(
                                    name = name.value,
                                    date = date.value,
                                    notes = "",
                                    elecAndAvi = true,
                                    idenAndCert = true,
                                    sysAndComp = true,
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
fun BoxForCreateFlights(title:String, state: MutableState<String>,){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Background)
            .padding(bottom = 15.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(width = 1.dp, color = BottomBarBorder, RoundedCornerShape(18.dp)),
        contentAlignment = Alignment.CenterStart

    ){
        Row {
            Text(text = title,
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
fun DateFlights(flightsDate: MutableState<String>){
    Row(
    ){
        Column(modifier = Modifier
            .padding(start = 8.dp)) {
            val context = LocalContext.current
            val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
            Text(
                text = "Check before",
                fontSize = 15.sp,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(45.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Background)
                    .border(width = 1.dp, color = BottomBarBorder, RoundedCornerShape(4.dp))
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

@Composable
fun CreateFlightsButton(
    title:String,
    name: String,
    date:String,
    showFlightsSheet:MutableState<Boolean>,
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