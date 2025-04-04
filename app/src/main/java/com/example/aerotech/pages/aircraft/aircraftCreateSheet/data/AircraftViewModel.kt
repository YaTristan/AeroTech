package com.example.aerotech.pages.aircraft.aircraftCreateSheet.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AircraftViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AircraftRepository
    val allAircrafts: LiveData<List<AircraftData>>

    private val _aircraftList = MutableStateFlow<List<AircraftData>>(emptyList())
    val aircraftList: StateFlow<List<AircraftData>> = _aircraftList

    private val _itemCount = MutableLiveData<Int>()
    val itemCount: LiveData<Int> = _itemCount

    init {
        val database = AircraftDatabase.getDatabase(application)
        repository = AircraftRepository(database.aircraftDao())
        allAircrafts = repository.allAircrafts.asLiveData()

        // Запускаем корутину для обновления списка при изменении данных в репозитории
        viewModelScope.launch {
            repository.allAircrafts.collect { aircrafts ->
                _aircraftList.value = aircrafts
            }
        }

        // Инициализируем подсчет
        viewModelScope.launch {
            _itemCount.value = repository.getCount()
        }
    }

    /** Inserts an aircraft into the repository. */
    fun insertAircraft(aircraft: AircraftData) = viewModelScope.launch {
        repository.insertAircraft(aircraft)
        _itemCount.value = repository.getCount() // обновляем itemCount после вставки
    }

    /** Deletes an aircraft from the repository. */
    fun deleteAircraft(aircraft: AircraftData) = viewModelScope.launch {
        repository.deleteAircraft(aircraft)
        _itemCount.value = repository.getCount() // обновляем itemCount после удаления
    }

    /** Updates an aircraft in the repository. */
    fun updateAircraft(aircraft: AircraftData) = viewModelScope.launch {
        repository.updateAircraft(aircraft)
    }

    /** Fetches an aircraft by its ID. */
    fun getAircraftById(id: Int): LiveData<AircraftData?> = liveData {
        emit(repository.getAircraftById(id))
    }
}
