package com.example.aerotech.pages.flights.flightsCreateSheet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightsDao {
    @Query("SELECT * FROM flights ORDER BY id ASC")
    fun getAllFlights(): Flow<List<FlightsData>>

    @Query("SELECT COUNT(*) FROM flights WHERE isReported = :isReported")
    suspend fun getCountFlightsByStatus(isReported: Boolean): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlights(flights: FlightsData)

    @Delete
    suspend fun deleteFlights(flights: FlightsData)

    @Update
    suspend fun updateFlights(flights: FlightsData)

    @Query("SELECT * FROM flights WHERE id = :id")
    suspend fun getFlightsById(id: Int): FlightsData?

    @Query("SELECT * FROM flights WHERE isReported = true")
    fun getReportedFlights(): Flow<List<FlightsData>>

    @Query("SELECT * FROM flights WHERE isReported = false")
    fun getUnreportedFlights(): Flow<List<FlightsData>>

    @Query("SELECT COUNT(*) FROM flights") // Убедитесь, что имя таблицы соответствует вашей
    suspend fun getCountFlights(): Int

    // New methods to get counts of reported and unreported flights
    @Query("SELECT COUNT(*) FROM flights WHERE isReported = true")
    suspend fun getCountReportedFlights(): Int

    @Query("SELECT COUNT(*) FROM flights WHERE isReported = false")
    suspend fun getCountUnreportedFlights(): Int

    // New methods to get all reported and unreported flights
    @Query("SELECT * FROM flights WHERE isReported = true ORDER BY id ASC")
    fun getAllReportedFlights(): Flow<List<FlightsData>>

    @Query("SELECT * FROM flights WHERE isReported = false ORDER BY id ASC")
    fun getAllUnreportedFlights(): Flow<List<FlightsData>>
}
