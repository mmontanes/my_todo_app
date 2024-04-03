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
    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toTaskEntity())
    }
    suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toTaskEntity())
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toTaskEntity())
    }
}

fun TaskModel.toTaskEntity(): TaskEntity = TaskEntity(
    id = this.id,
    content = this.content,
    done = this.done
)
