package com.example.aerotech.pages.aircraft.aircraftCreateSheet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AircraftDao {

    @Query("SELECT * FROM aircraft ORDER BY id ASC")
    fun getAllAircrafts(): Flow<List<AircraftData>>

    @Query("SELECT COUNT(*) FROM aircraft")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAircraft(aircraft: AircraftData)

    @Delete
    suspend fun deleteAircraft(aircraft: AircraftData)

    @Update
    suspend fun updateAircraft(aircraft: AircraftData)

    @Query("SELECT * FROM aircraft WHERE id = :id")
    suspend fun getAircraftById(id: Int): AircraftData?
}