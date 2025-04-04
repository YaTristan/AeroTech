package com.example.aerotech.pages.flights.flightsCreateSheet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftDao
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftDatabase

@Database(entities = [FlightsData::class], version = 1, exportSchema = false)
abstract class FlightsDatabase: RoomDatabase() {
        abstract fun flightsDao(): FlightsDao

        companion object {
            @Volatile
            private var INSTANCE: FlightsDatabase? = null

            fun getDatabase(context: Context): FlightsDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FlightsDatabase::class.java,
                        "flights_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
}