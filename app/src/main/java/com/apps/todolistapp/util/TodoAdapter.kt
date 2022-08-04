package com.apps.todolistapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.todolistapp.R
import com.apps.todolistapp.databinding.ItemTodoBinding
import com.apps.todolistapp.model.Todo

class TodoAdapter(var todos: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        holder.binding.tvTodoTitle.text =
            todos[position].todoTitle //Getting the data, and set it on the view
        holder.binding.cbTodoDone.isChecked =
            todos[position].isChecked //Getting the data, and set it on the view

    }

    override fun getItemCount(): Int {
        return todos.size
    }

}