package com.example.mytodoapp.addtasks.domain

import com.example.mytodoapp.addtasks.data.TaskRepository
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}
