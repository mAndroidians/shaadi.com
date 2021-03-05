package com.shaadi.data.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "user_entity",
    primaryKeys = ["id"]
)
data class UserEntity(
    @field:SerializedName("id")
    val id:Int,
    @field:SerializedName("name")
    val name:String
)