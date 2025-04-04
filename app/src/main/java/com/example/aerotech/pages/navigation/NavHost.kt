package com.example.aerotech.pages.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aerotech.pages.aircraft.AircraftPage
import com.example.aerotech.pages.analytics.AnalyticsPage
import com.example.aerotech.pages.flights.FlightsPage
import com.example.aerotech.pages.monitoring.MonitoringPage
import com.example.aerotech.pages.settings.SettingsPage


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigator() {
    val navController = rememberNavController()

    // Состояние для текущего маршрута
    var currentRoute by remember { mutableStateOf(Routes.aircraftPage) }

    NavHost(navController = navController, startDestination = Routes.aircraftPage) {
        composable(Routes.aircraftPage) {
            AircraftPage(navController)
        }
        composable(Routes.monitoringPage) {
            MonitoringPage(navController)
        }
        composable(Routes.flightsPage) {
            FlightsPage(navController)
        }
        composable(Routes.analyticsPage) {
            AnalyticsPage(navController)
        }
        composable(Routes.settingsPage) {
            SettingsPage(navController)
        }
    }

    // Анимация перехода
    AnimatedContent(
        targetState = currentRoute,
        transitionSpec = {
            // Определите анимацию для перехода
            if (targetState > initialState) {
                // Если целевое состояние больше (например, MonitoringPage)
                slideInHorizontally(initialOffsetX = { width -> width }) with
                        slideOutHorizontally(targetOffsetX = { width -> -width })
            } else {
                // Если целевое состояние меньше (например, AircraftPage)
                slideInHorizontally(initialOffsetX = { width -> -width }) with
                        slideOutHorizontally(targetOffsetX = { width -> width })
            }

        }
    ) { targetState ->
        // Обновите содержимое на основе текущего маршрута
        when (targetState) {
            Routes.aircraftPage -> AircraftPage(navController)
            Routes.monitoringPage -> MonitoringPage(navController)
            Routes.flightsPage -> FlightsPage(navController)
            Routes.analyticsPage -> AnalyticsPage(navController)
            Routes.settingsPage -> SettingsPage(navController)
        }
    }

    // Обновите текущий маршрут при переходе
    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute = destination.route ?: Routes.aircraftPage
    }
}