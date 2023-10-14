package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")

data class User(
    @ColumnInfo(name = "username") var username:String,
    @ColumnInfo(name = "email") var email:String,
    @ColumnInfo(name = "password") var password:String,
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
/*data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val email: String,
    val password: String
)*/

