package com.example.aerotech.pages.flights.flightsCreateSheet.data

import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import kotlinx.coroutines.flow.Flow

class FlightsRepository(private val flightsDao: FlightsDao) {
    val allFlights: Flow<List<FlightsData>> = flightsDao.getAllFlights()

    val allReported: Flow<List<FlightsData>> = flightsDao.getAllReportedFlights()
    val allUnreported: Flow<List<FlightsData>> = flightsDao.getAllUnreportedFlights()

    suspend fun insertFlights(flights: FlightsData) {
        flightsDao.insertFlights(flights)
    }

    suspend fun getFlightsById(id: Int): FlightsData? = flightsDao.getFlightsById(id)

    suspend fun deleteFlights(flights: FlightsData) {
        flightsDao.deleteFlights(flights)
    }

    suspend fun updateFlights(flights: FlightsData) {
        flightsDao.updateFlights(flights)
    }

    suspend fun getCountFlights(): Int {
        return flightsDao.getCountFlights()
    }

    // New methods to get counts and all flights
    suspend fun getCountReportedFlights(): Int {
        return flightsDao.getCountReportedFlights()
    }

    suspend fun getCountUnreportedFlights(): Int {
        return flightsDao.getCountUnreportedFlights()
    }
}
