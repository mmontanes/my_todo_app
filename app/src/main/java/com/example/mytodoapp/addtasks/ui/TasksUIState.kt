package com.example.mytodoapp.addtasks.ui

import com.example.mytodoapp.addtasks.ui.model.TaskModel

sealed interface TasksUIState {
    object Loading : TasksUIState
    data class Error(val throwable: Throwable) : TasksUIState
    data class Success(val tasks: List<TaskModel>) : TasksUIState
}
