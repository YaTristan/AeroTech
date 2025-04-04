package com.example.aerotech.pages.monitoring.monitoringCreateSheet.data

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

class MonitoringViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MonitoringRepository
    val allMonitoring: LiveData<List<MonitoringData>>

    private val _monitoringList = MutableStateFlow<List<MonitoringData>>(emptyList())
    val monitoringList: StateFlow<List<MonitoringData>> = _monitoringList

    private val _itemCountMonitoring = MutableLiveData<Int>()
    val itemCountMonitoring: LiveData<Int> = _itemCountMonitoring

    init {
        val database = MonitoringDatabase.getDatabase(application)
        repository = MonitoringRepository(database.monitoringDao())
        allMonitoring = repository.allMonitoring.asLiveData()

        // Запускаем корутину для обновления списка при изменении данных в репозитории
        viewModelScope.launch {
            repository.allMonitoring.collect { monitoring->
                _monitoringList.value = monitoring
            }
        }

        // Инициализируем подсчет
        viewModelScope.launch {
            _itemCountMonitoring.value = repository.getCountMonitoring()
        }
    }

    /** Inserts an aircraft into the repository. */
    fun insertMonitoring(monitoring: MonitoringData) = viewModelScope.launch {
        repository.insertMonitoring(monitoring)
        _itemCountMonitoring.value = repository.getCountMonitoring() // обновляем itemCount после вставки
    }

    /** Deletes an aircraft from the repository. */
    fun deleteMonitoring(monitoring: MonitoringData) = viewModelScope.launch {
        repository.deleteMonitoring(monitoring)
        _itemCountMonitoring.value = repository.getCountMonitoring() // обновляем itemCount после удаления
    }

    /** Updates an aircraft in the repository. */
    fun updateMonitoring(monitoring: MonitoringData) = viewModelScope.launch {
        repository.updateMonitoring(monitoring)
    }

    /** Fetches an aircraft by its ID. */
    fun getMonitoringById(id: Int): LiveData<MonitoringData?> = liveData {
        emit(repository.getMonitoringById(id))
    }
}