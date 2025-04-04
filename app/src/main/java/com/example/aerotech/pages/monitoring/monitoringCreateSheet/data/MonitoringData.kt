package com.example.aerotech.pages.monitoring.monitoringCreateSheet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monitoring")
data class MonitoringData(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name:String,
    val weight:String,
    val balance:Boolean,
    val temperature:String,
    val pressure:String,
    val consumption:String
)
