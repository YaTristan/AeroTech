package com.example.aerotech.pages.aircraft.aircraftCreateSheet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aircraft")
data class AircraftData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val model: String?,
    val serial: String?,
    val lastInspection: String?,
    val upcomingInspection: String?
)
