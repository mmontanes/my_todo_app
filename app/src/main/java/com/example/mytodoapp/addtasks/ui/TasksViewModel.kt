package com.example.mytodoapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    // Esta solución va mejor que los LiveData normales.
    // Una alternativa a esta solución es usar Flow.
    // TODO: Implementar Flow
    private val _tasksList = mutableStateListOf<TaskModel>()
    val tasksList: List<TaskModel> = _tasksList

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onDismissDialog() {
        _showDialog.value = false
    }

    fun onTaskAdded(task: String) {
        // Add task to the database
        _tasksList.add(TaskModel(content = task))
        _showDialog.value = false
    }
}
