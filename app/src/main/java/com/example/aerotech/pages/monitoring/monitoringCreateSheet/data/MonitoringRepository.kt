package com.example.aerotech.pages.monitoring.monitoringCreateSheet.data

import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import kotlinx.coroutines.flow.Flow

class MonitoringRepository(private val monitoringDao:MonitoringDao) {

    val allMonitoring: Flow<List<MonitoringData>> = monitoringDao.getAllMonitoring()

    suspend fun insertMonitoring(monitoring: MonitoringData) {
        monitoringDao.insertMonitoring(monitoring)
    }

    suspend fun getMonitoringById(id: Int): MonitoringData? = monitoringDao.getMonitoringById(id)

    suspend fun deleteMonitoring(monitoring: MonitoringData) {
        monitoringDao.deleteMonitoring(monitoring)
    }

    suspend fun updateMonitoring(monitoring: MonitoringData) {
        monitoringDao.updateMonitoring(monitoring)
    }

    suspend fun getCountMonitoring(): Int {
        return monitoringDao.getCountMonitoring()
    }
}