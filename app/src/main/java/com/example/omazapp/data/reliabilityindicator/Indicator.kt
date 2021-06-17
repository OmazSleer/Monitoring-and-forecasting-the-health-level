package com.example.omazapp.data.reliabilityindicator

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "indicator_table")
class Indicator(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int?,
    val number: Float?,
    val timer: Long?
): Parcelable

