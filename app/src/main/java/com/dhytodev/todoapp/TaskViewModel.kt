package com.dhytodev.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TaskViewModel(
    private val database: TaskDatabase
): ViewModel() {

    fun addNewTask(task : Task) = database.taskDao().insertTask(task)

    fun getAllTasks() : LiveData<List<Task>> = database.taskDao().getTasks()

    fun deleteTask(task: Task) = database.taskDao().delete(task)

    fun updateTask(task: Task) = database.taskDao().updateTask(task.completed, task.task, task.id)
}
