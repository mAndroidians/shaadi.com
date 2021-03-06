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
    val name:String,
    @field:SerializedName("gender")
    val gender:String,
    @field:SerializedName("age")
    val age:Int,
    @field:SerializedName("picture")
    val picture:String,
    @field:SerializedName("accepted")
    val accepted:Int = 0

)