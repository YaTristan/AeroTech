package com.example.aerotech.pages.aircraft.aircraftCreateSheet

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BottomBarBorder
import com.example.aerotech.ui.theme.TabFont
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AircraftCreateSheet(isVisible:Boolean,onDismiss: () -> Unit) {

    val aircraftViewModel: AircraftViewModel = viewModel()
    var name  = remember{ mutableStateOf("") }
    var model  = remember{ mutableStateOf("") }
    var serialNumber  = remember{ mutableStateOf("") }
    var lastInspection  = remember{ mutableStateOf("") }
    var upcomingInspection  = remember{ mutableStateOf("") }

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
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "New aircraft",
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
                        title = "Create",
                        name.value,
                        model.value,
                        serialNumber.value,
                        lastInspection.value,
                        upcomingInspection.value,
                        showAircraftSheet = showAircraftSheet,
                        onCreate = {
                            aircraftViewModel.insertAircraft(
                                AircraftData(
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

@Composable
fun BoxForCreateAircraft(title:String, state:MutableState<String>,){
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
                    capitalization = if(title == "Serial number") KeyboardCapitalization.Characters else KeyboardCapitalization.Words
                    , imeAction = ImeAction.Done
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
fun InspectionDate(lastInspection:MutableState<String>, upcomingInspection:MutableState<String>){
    Row(
    ){
        Column(modifier = Modifier
            .padding(start = 8.dp)) {
            val context = LocalContext.current
            val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
            Text(
                text = "Last inspection",
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
                        val datePickerDialog = DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                            // Обновляем календарь с выбранной датой
                            calendar.set(selectedYear, selectedMonth, selectedDay)
                            // Форматируем выбранную дату и обновляем состояние
                            lastInspection.value = dateFormat.format(calendar.time)
                        }, year, month, day)


                        // Показываем диалог выбора даты
                        datePickerDialog.show()
                    },
                contentAlignment = Alignment.Center) {

                Text(text = lastInspection.value,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = TabFont,
                    fontSize = 17.sp)
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = Modifier
            .padding(start = 8.dp, end = 14.dp)
        ) {
            val context = LocalContext.current
            val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
            Text(
                text = "Upcoming inspection",
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
                        val datePickerDialog = DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                            // Обновляем календарь с выбранной датой
                            calendar.set(selectedYear, selectedMonth, selectedDay)
                            // Форматируем выбранную дату и обновляем состояние
                            upcomingInspection.value = dateFormat.format(calendar.time)
                        }, year, month, day)


                        // Показываем диалог выбора даты
                        datePickerDialog.show()
                    },
                contentAlignment = Alignment.Center) {

                Text(text = upcomingInspection.value,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = TabFont,
                    fontSize = 17.sp)
            }
        }
    }
}

@Composable
fun CreateButton(
    title:String,
    name: String,
    model: String,
    serial: String,
    lastInspection: String,
    upcomingInspection: String,
    showAircraftSheet: MutableState<Boolean>,
    onCreate: () -> Unit,
    onDismiss: () -> Unit){
    var alpha = name.isNullOrBlank()||model.isNullOrBlank()||serial.isNullOrBlank()||lastInspection.isNullOrBlank()||upcomingInspection.isNullOrBlank()
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .alpha(if(alpha) 0.6f else 1f)
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