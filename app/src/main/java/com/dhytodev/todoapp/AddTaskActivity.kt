package com.dhytodev.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTaskActivity : AppCompatActivity() {

    private val taskViewModel : TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        buttonSaveTask.setOnClickListener {
            saveTask()
            finish()
        }
    }

    private fun saveTask(){
        val taskName = textInputTask.text.toString()
        val taskChecked = checkboxLayout.isChecked

        val task = Task(task = taskName, completed = taskChecked)

        taskViewModel.addNewTask(task)
    }
}
