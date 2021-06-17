package com.example.omazapp.data.measurement

import androidx.lifecycle.LiveData

class MeasurementRepository(private val measurementDao: MeasurementDao) {
    val readAllData: LiveData<List<Measurement>> = measurementDao.readAllData()

    suspend fun addMeasurement(user: Measurement) {
        measurementDao.addMeasurement(user)
    }

    suspend fun updateMeasurement(user: Measurement) {
        measurementDao.updateMeasurement(user)
    }

    suspend fun deleteMeaurement(measurement: Measurement) {
        measurementDao.deleteMeasurement(measurement)
    }

    suspend fun deleteAllMeaurement() {
        measurementDao.deleteAllMeasurement()
    }

}