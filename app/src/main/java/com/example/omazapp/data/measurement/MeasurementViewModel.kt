package com.example.omazapp.data.measurement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeasurementViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Measurement>>
    private val repository: MeasurementRepository

    init {
        val userDao = MeasurementDatabase.getDatabase(application).measurementDao()
        repository = MeasurementRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addMeasurement(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeasurement(measurement)
        }
    }

    fun updateMeasurement(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMeasurement(measurement)
        }
    }

    fun deleteMeasurement(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeaurement(measurement)
        }
    }

    fun deleteAllMeasurement() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMeaurement()
        }
    }


}