package com.example.omazapp.data.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val login: String?,
    val name: String?,
    val age: Int?,
    val country: String?,
    val gender: String?,
    val password: String?
): Parcelable