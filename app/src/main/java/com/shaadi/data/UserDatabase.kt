package com.shaadi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaadi.dao.UserDao
import com.shaadi.data.entity.UserEntity


@Database(
	entities = [
		(UserEntity::class)
	],
	version = 1,
	exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
	abstract fun janMantraTvNewsDao(): UserDao


}