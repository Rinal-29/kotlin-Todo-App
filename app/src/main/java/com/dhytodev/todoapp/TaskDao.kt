package com.dhytodev.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun getTasks() : LiveData<List<Task>>

    // update complete by id
    @Query("UPDATE tasks SET completed = :isCompleted, task = :taskName WHERE id = :id")
    fun updateTask(isCompleted: Boolean, taskName : String, id : Int)

    // delete by id
    @Query("DELETE FROM tasks WHERE id = :id")
    fun deleteRow(id: Int)

    //delete task
    @Query("DELETE FROM tasks")
    fun clearTask()
}