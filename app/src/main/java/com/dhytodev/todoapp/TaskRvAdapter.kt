package com.dhytodev.todoapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_task_item.view.*

class TaskRvAdapter(private val listener: (Task) -> Unit) : RecyclerView.Adapter<TaskRvAdapter.ViewHolder>() {

    var list = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_task_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    inner class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        fun bindItem(
            task: Task,
            listener: (Task) -> Unit
        ) {
            itemView.apply {
                checkboxComplete.isChecked = task.completed

                if (task.completed){
                    textTaskName.text = task.task
                    textTaskName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                textTaskName.text = task.task
            }

            itemView.setOnClickListener {
                listener(task)
            }
        }
    }
}