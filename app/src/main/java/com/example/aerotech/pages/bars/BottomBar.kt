package com.example.aerotech.pages.bars

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Airlines
import androidx.compose.material.icons.rounded.AirplanemodeActive
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.ConnectingAirports
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aerotech.pages.bars.bottomBarSections.ElementsSection
import com.example.aerotech.pages.data.BottomBarItems
import com.example.aerotech.pages.navigation.Routes
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BottomBarBorder
import androidx.lifecycle.viewmodel.compose.viewModel

val bottomBarItems = listOf(
    BottomBarItems(
        id = 0,
        title = "Maintenance",
        icon = Icons.Rounded.AirplanemodeActive,
        route = Routes.aircraftPage
    ),
    BottomBarItems(
        id = 1,
        title = "Monitoring",
        icon = Icons.Rounded.Airlines,
        route = Routes.monitoringPage
    ),
    BottomBarItems(
        id = 2,
        title = "Flights",
        icon = Icons.Rounded.ConnectingAirports,
        route = Routes.flightsPage
    ),
    BottomBarItems(
        id = 3,
        title = "Analytics",
        icon = Icons.Rounded.Analytics,
        route = Routes.analyticsPage
    ),
    BottomBarItems(
        id = 4,
        title = "Settings",
        icon = Icons.Rounded.Settings,
        route = Routes.settingsPage
    ),
)

@Composable
fun BottomBar(navController: NavController,viewModel: BottomBarViewModel = viewModel()){
    val selectedId by viewModel.selectedId.collectAsState()
    var previousId by remember { mutableStateOf(-1) }
    val currentRoute = navController.currentDestination?.route ?: ""
    viewModel.initializeSelectedId(currentRoute)

    LaunchedEffect(selectedId) {
        previousId = selectedId
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .clip(RoundedCornerShape(topEnd = 62.dp))
            .border(
                BorderStroke(2.dp, BottomBarBorder),
                RoundedCornerShape(topEnd = 62.dp)
            )
            .background(Background)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .clip(RoundedCornerShape(topEnd = 79.dp))
            .padding(top = 2.dp, end = 0.dp)
            .background(Background)
    ) {
        LazyRow(
            modifier = Modifier.align(Alignment.Center).padding(end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ){
            items(bottomBarItems) { item->
                ElementsSection(id = item.id, name = item.title, icon = item.icon, selectedId = selectedId, onSelect = { newId ->
                    viewModel.updateSelectedId(newId)
                    navController.navigate(item.route) // Перемещаем навигацию в onSelect
                }, route = item.route, navController = navController)
            }
        }
    }
}