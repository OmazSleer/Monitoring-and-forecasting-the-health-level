package com.example.omazapp.data.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table WHERE login == :str")
    fun getCurrentUser(str: String):User
}