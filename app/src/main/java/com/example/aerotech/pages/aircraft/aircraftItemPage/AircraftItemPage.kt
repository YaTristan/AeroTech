package com.example.aerotech.pages.aircraft.aircraftItemPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aerotech.ui.theme.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AircraftItemPage(isVisible:Boolean, aircraftId:Int, onDismiss: () -> Unit ){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        containerColor = Background,
        sheetState = sheetState,
        onDismissRequest = { onDismiss() }
    ) {
        AircraftItemSection(aircraftId, isVisible, onDismiss)
    }
}