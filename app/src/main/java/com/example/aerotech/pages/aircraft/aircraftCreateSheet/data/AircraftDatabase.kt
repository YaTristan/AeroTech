package com.example.aerotech.pages.aircraft.aircraftCreateSheet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AircraftData::class], version = 1, exportSchema = false)
abstract class AircraftDatabase : RoomDatabase() {

    abstract fun aircraftDao(): AircraftDao

    companion object {
        @Volatile
        private var INSTANCE: AircraftDatabase? = null

        fun getDatabase(context: Context): AircraftDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AircraftDatabase::class.java,
                    "aircraft_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}