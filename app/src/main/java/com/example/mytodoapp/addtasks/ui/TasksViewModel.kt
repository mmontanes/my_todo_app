package com.example.mytodoapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.addtasks.domain.AddTaskUseCase
import com.example.mytodoapp.addtasks.domain.GetTaskUseCase
import com.example.mytodoapp.addtasks.ui.TasksUIState.*
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUIState> = getTaskUseCase().map (::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

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

        viewModelScope.launch {
            addTaskUseCase(TaskModel(content = task))
        }
    }

    fun onTaskCheckBoxClicked(taskModel: TaskModel) {
        val index = _tasksList.indexOf(taskModel)
        _tasksList[index] = taskModel.copy(done = !taskModel.done)
    }

    fun onDeleteTask(taskModel: TaskModel) {
        val task = _tasksList.find { it.id == taskModel.id }
        _tasksList.remove(task)
    }
}
