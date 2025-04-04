package com.example.aerotech.pages.analytics.analyticsItemPage

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.aerotech.pages.flights.flightsItemPage.FlightsItemSection
import com.example.aerotech.ui.theme.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsItemPage(isVisible:Boolean, flightsId:Int, onDismiss: () -> Unit ){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        containerColor = Background,
        sheetState = sheetState,
        onDismissRequest = { onDismiss() }
    ) {
        AnalyticsItemSection(isVisible, flightsId, onDismiss)
    }
}