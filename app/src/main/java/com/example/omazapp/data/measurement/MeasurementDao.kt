package com.example.omazapp.data.measurement

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.omazapp.data.user.User

@Dao
interface MeasurementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeasurement(user: Measurement)

    @Query("SELECT * FROM measurement_table")
    fun readAllData(): LiveData<List<Measurement>>

    @Delete
    suspend fun deleteMeasurement(measurement: Measurement)

    @Query("DELETE FROM measurement_table")
    suspend fun deleteAllMeasurement()

    @Update
    suspend fun updateMeasurement(measurement: Measurement)

}