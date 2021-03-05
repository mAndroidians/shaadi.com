package com.shaadi.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.data.entity.UserEntity


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlll(userEntity: ArrayList<UserEntity>)

    @Query("SELECT * FROM user_entity")
    fun getUserData(): LiveData<List<UserEntity>>

    @Query(value = "DELETE FROM user_entity")
    fun deleteAll()

}