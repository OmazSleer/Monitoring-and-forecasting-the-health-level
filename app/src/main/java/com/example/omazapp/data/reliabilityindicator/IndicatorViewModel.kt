package com.example.omazapp.data.reliabilityindicator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndicatorViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Indicator>>
    private val repository: IndicatorRepository

    init {
        val indicatorDao = IndicatorDatabase.getDatabase(application).indicatorDao()
        repository = IndicatorRepository(indicatorDao)
        readAllData = repository.readAllData
    }

    fun addIndicator(indicator: Indicator) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIndicator(indicator)
        }
    }

    fun updateIndicator(indicator: Indicator) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIndicator(indicator)
        }
    }

    fun deleteIndicator(indicator: Indicator) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIndicator(indicator)
        }
    }

    fun deleteAllIndicator() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllIndicator()
        }
    }


}