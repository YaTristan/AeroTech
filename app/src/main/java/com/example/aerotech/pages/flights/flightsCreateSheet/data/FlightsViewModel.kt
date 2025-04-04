package com.example.aerotech.pages.flights.flightsCreateSheet.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftData
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftDatabase
import com.example.aerotech.pages.aircraft.aircraftCreateSheet.data.AircraftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FlightsRepository
    val allFlights: LiveData<List<FlightsData>>

    val allReported: LiveData<List<FlightsData>>

    val allUnreported: LiveData<List<FlightsData>>

    private val _flightsList = MutableStateFlow<List<FlightsData>>(emptyList())
    val flightsList: StateFlow<List<FlightsData>> = _flightsList

    private val _itemCountFlights = MutableLiveData<Int>()
    val itemCountFlights: LiveData<Int> = _itemCountFlights

    private val _unreportedFlightsCount = MutableLiveData<Int>()
    val unreportedFlightsCount: LiveData<Int> get() = _unreportedFlightsCount

    private val _reportedFlightsCount = MutableLiveData<Int>()
    val reportedFlightsCount: LiveData<Int> get() = _reportedFlightsCount

    init {
        val database = FlightsDatabase.getDatabase(application)
        repository = FlightsRepository(database.flightsDao())
        allFlights = repository.allFlights.asLiveData()

        allReported = repository.allReported.asLiveData()
        allUnreported = repository.allUnreported.asLiveData()
        // Запускаем корутину для обновления списка при изменении данных в репозитории
        updateFlightCounts()
        getCountReportedFlights()
        getCountUnreportedFlights()
    }

    private fun updateFlightCounts() = viewModelScope.launch {
        _reportedFlightsCount.value = repository.getCountReportedFlights()
        _unreportedFlightsCount.value = repository.getCountUnreportedFlights()
    }

    /** Inserts an aircraft into the repository. */
    fun insertFlights(flights: FlightsData) = viewModelScope.launch {
        repository.insertFlights(flights)
        updateFlightCounts() // обновляем itemCount после вставки
    }

    /** Deletes an aircraft from the repository. */
    fun deleteFlights(flights: FlightsData) = viewModelScope.launch {
        repository.deleteFlights(flights)
        _itemCountFlights.value = repository.getCountFlights() // обновляем itemCount после удаления
    }

    /** Updates an aircraft in the repository. */
    fun updateFlights(flights: FlightsData) = viewModelScope.launch {
        // Получаем обновленные данные рейсов
        val updatedFlights = repository.updateFlights(flights)
        // Обновляем количество неотчетных рейсов
        updateFlightCounts()
    }

    fun getFlightsById(id: Int): LiveData<FlightsData?> = liveData {
        emit(repository.getFlightsById(id))
    }

    fun getCountFlights(): LiveData<Int> = liveData {
        emit(repository.getCountFlights()) // Получаем количество рейсов из репозитория
    }

    fun getCountReportedFlights(): LiveData<Int> = liveData {
        _reportedFlightsCount.value = repository.getCountReportedFlights()  // Получаем количество рейсов из репозитория
    }

    fun getCountUnreportedFlights(): LiveData<Int> = liveData {
        _unreportedFlightsCount.value = repository.getCountUnreportedFlights() // Получаем количество рейсов из репозитория
    }

}