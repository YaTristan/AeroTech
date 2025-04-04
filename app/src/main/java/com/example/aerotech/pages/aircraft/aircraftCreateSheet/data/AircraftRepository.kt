package com.example.aerotech.pages.aircraft.aircraftCreateSheet.data

import kotlinx.coroutines.flow.Flow

class AircraftRepository(private val aircraftDao: AircraftDao) {

    val allAircrafts: Flow<List<AircraftData>> = aircraftDao.getAllAircrafts()

    suspend fun insertAircraft(aircraft: AircraftData) {
        aircraftDao.insertAircraft(aircraft)
    }

    suspend fun getAircraftById(id: Int): AircraftData? = aircraftDao.getAircraftById(id)

    suspend fun deleteAircraft(aircraft: AircraftData) {
        aircraftDao.deleteAircraft(aircraft)
    }

    suspend fun updateAircraft(aircraft: AircraftData) {
        aircraftDao.updateAircraft(aircraft)
    }

    suspend fun getCount(): Int {
        return aircraftDao.getCount()
    }
}