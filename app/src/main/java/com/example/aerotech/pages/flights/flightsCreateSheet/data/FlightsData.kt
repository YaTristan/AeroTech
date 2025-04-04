package com.example.aerotech.pages.flights.flightsCreateSheet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class FlightsData(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name:String,
    val date:String,
    val sysAndComp:Boolean,
    val elecAndAvi:Boolean,
    val idenAndCert:Boolean,
    val notes:String,
    val isReported:Boolean = false,
)
