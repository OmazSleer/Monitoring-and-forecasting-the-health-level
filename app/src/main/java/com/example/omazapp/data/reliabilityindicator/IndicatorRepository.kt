package com.example.omazapp.data.reliabilityindicator

import androidx.lifecycle.LiveData

class IndicatorRepository(private val indicatorDao: IndicatorDao) {
    val readAllData: LiveData<List<Indicator>> = indicatorDao.readAllData()

    suspend fun addIndicator(indicator: Indicator) {
        indicatorDao.addIndicator(indicator)
    }

    suspend fun updateIndicator(indicator: Indicator) {
        indicatorDao.updateIndicator(indicator)
    }

    suspend fun deleteIndicator(indicator: Indicator) {
        indicatorDao.deleteIndicator(indicator)
    }

    suspend fun deleteAllIndicator() {
        indicatorDao.deleteAllIndicator()
    }

}