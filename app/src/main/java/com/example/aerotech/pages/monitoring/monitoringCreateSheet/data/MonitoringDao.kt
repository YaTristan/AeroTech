package com.example.aerotech.pages.monitoring.monitoringCreateSheet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import kotlinx.coroutines.flow.Flow

@Dao
interface MonitoringDao {

    @Query("SELECT * FROM monitoring ORDER BY id ASC")
    fun getAllMonitoring(): Flow<List<MonitoringData>>

    @Query("SELECT COUNT(*) FROM monitoring")
    suspend fun getCountMonitoring(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonitoring(monitoring: MonitoringData)

    @Delete
    suspend fun deleteMonitoring(monitoring: MonitoringData)

    @Update
    suspend fun updateMonitoring(monitoring: MonitoringData)

    @Query("SELECT * FROM monitoring WHERE id = :id")
    suspend fun getMonitoringById(id: Int): MonitoringData?
}