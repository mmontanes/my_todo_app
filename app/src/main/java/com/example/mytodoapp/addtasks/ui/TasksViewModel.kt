package com.example.mytodoapp.addtasks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onDismissDialog() {
        _showDialog.value = false
    }

    fun onTaskAdded(task: String) {
        // Add task to the database
        Log.i("TasksViewModel", "Task added: $task")
        _showDialog.value = false
    }
}
