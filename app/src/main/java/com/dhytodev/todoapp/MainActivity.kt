package com.dhytodev.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val taskViewModel : TaskViewModel by viewModel()
    private val taskRvAdapter: TaskRvAdapter by lazy {
        TaskRvAdapter{
            val intent = Intent(applicationContext, UpdateTaskActivity::class.java)
            var bundle = Bundle()
            bundle.putParcelable("TASK", it)
            intent.putExtra("myBundle", bundle)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insertTask()
        observeTasks()
        initView()
    }

    private fun initView(){
        recyclerViewTasks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskRvAdapter
        }
        fabAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun insertTask(){
        val task1 = Task(1, "Belajar Android")
        val task2 = Task(2, "Belajar Java", true)
        taskViewModel.addNewTask(task1)
        taskViewModel.addNewTask(task2)
    }

    private fun observeTasks(){
        taskViewModel.getAllTasks().observe(this, Observer {
            if (it.isNotEmpty()){
                taskRvAdapter.list = it
                taskRvAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        observeTasks()
        initView()
    }
}
