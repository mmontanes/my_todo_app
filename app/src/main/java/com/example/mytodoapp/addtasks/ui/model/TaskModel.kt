package com.example.mytodoapp.addtasks.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val content: String,
    var done: Boolean = false
)
