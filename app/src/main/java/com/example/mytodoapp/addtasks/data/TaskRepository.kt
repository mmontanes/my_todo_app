package com.example.mytodoapp.addtasks.data

import com.example.mytodoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getAllTasks().map {
        it.map { taskEntity ->
            TaskModel(
                taskEntity.id,
                taskEntity.content,
                taskEntity.done
            )
        }
    }
    suspend fun addTask(taskModel: TaskModel) = taskDao.addTask(
        TaskEntity(
            taskModel.id,
            taskModel.content,
            taskModel.done
        )
    )

    suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(
            TaskEntity(
                id = taskModel.id,
                content = taskModel.content,
                done = taskModel.done
            )
        )
    }
}
