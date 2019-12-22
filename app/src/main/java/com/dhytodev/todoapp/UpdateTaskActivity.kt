package com.dhytodev.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import kotlinx.android.synthetic.main.activity_update_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateTaskActivity : AppCompatActivity() {

    private val taskViewModel : TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        val bundle= intent.getBundleExtra("myBundle")
        val taskItem = bundle?.getParcelable<Task>("TASK")

        val idTask = taskItem?.id
        val textTask = taskItem?.task.toString()
        val activated = taskItem?.completed
        val editAbleText = Editable.Factory.getInstance().newEditable(textTask)

        textInputUpdateTask.text = editAbleText
        if (activated != null) {
            checkboxTask.isChecked = activated
        }

        btnDelete.setOnClickListener {
            if (taskItem != null) {
                deleteTask(taskItem)
            }
        }

        btnSaveChanges.setOnClickListener {  updateTask(idTask) }
    }

    private fun deleteTask(task : Task){
        taskViewModel.deleteTask(task)
        finish()
    }

    private fun updateTask(idTask: Int?) {
        val task = textInputUpdateTask.text.toString()
        val check = checkboxTask.isChecked
        val newTask = idTask?.let { Task(it, task, check) }

        Log.e("Cek bool", check.toString())
        newTask?.let { taskViewModel.updateTask(it) }
        finish()
    }
}
