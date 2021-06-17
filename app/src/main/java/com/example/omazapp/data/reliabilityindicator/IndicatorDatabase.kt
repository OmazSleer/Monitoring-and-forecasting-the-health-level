package com.example.omazapp.data.reliabilityindicator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Indicator::class], version = 1, exportSchema = false)
abstract class IndicatorDatabase: RoomDatabase() {

    abstract fun indicatorDao(): IndicatorDao

    companion object {
        @Volatile
        private var INSTANCE: IndicatorDatabase? = null

        fun getDatabase(context: Context): IndicatorDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IndicatorDatabase::class.java,
                    "indicator_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}