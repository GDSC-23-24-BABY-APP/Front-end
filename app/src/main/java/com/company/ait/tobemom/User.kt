package com.company.ait.tobemom

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "id") var id: String,
    @SerializedName(value = "password") var pw: String,
) {
    @PrimaryKey(autoGenerate = true) var userId : Int = 0
}