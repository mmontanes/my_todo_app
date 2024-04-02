package com.example.mytodoapp.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey
    val id: Int,
    val content: String,
    var done: Boolean = false
)
