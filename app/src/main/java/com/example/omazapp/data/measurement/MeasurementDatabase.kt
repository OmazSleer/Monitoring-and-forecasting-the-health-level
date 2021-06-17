package com.example.omazapp.data.measurement

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Measurement::class], version = 1, exportSchema = false)
abstract class MeasurementDatabase: RoomDatabase() {

    abstract fun measurementDao(): MeasurementDao

    companion object {
        @Volatile
        private var INSTANCE: MeasurementDatabase? = null

        fun getDatabase(context: Context): MeasurementDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MeasurementDatabase::class.java,
                    "measurement_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}