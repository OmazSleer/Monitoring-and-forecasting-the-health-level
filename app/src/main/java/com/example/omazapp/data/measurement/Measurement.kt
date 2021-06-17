package com.example.omazapp.data.measurement

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "measurement_table")
class Measurement(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int?,
    val number: Int?,
    val timer: Long?
): Parcelable

