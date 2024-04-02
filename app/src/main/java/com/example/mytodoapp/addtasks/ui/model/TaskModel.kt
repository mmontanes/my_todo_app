package com.example.mytodoapp.addtasks.ui.model

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val content: String,
    var done: Boolean = false
)
