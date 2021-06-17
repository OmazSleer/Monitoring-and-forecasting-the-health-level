package com.example.omazapp.data.reliabilityindicator

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IndicatorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIndicator(user: Indicator)

    @Query("SELECT * FROM indicator_table")
    fun readAllData(): LiveData<List<Indicator>>

    @Delete
    suspend fun deleteIndicator(indicator: Indicator)

    @Query("DELETE FROM indicator_table")
    suspend fun deleteAllIndicator()

    @Update
    suspend fun updateIndicator(indicator: Indicator)

}