package com.apps.todolistapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.todolistapp.databinding.ActivityMainBinding
import com.apps.todolistapp.model.Todo
import com.apps.todolistapp.util.TodoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val todolist = mutableListOf(
            Todo("Go To Gym",false),
            Todo("Go To market",false),
            Todo("Go To college",false),
            Todo("Go To course",false),
            Todo("Go To stadium",false),
            Todo("Go To Gym2",false),
            Todo("Go To Gym3",false),
            Todo("Go To Gym4",false),
            Todo("Go To Gym5",false),
        )

        val ourAdapter = TodoAdapter(todolist)

        binding.rvTodos.adapter = ourAdapter

        binding.rvTodos.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
          val newTodoTitle = binding.etTodo.text.toString()
            val newTodo = Todo(newTodoTitle,false)
            todolist.add(newTodo)
            ourAdapter.notifyDataSetChanged()
            binding.etTodo.text.clear()


        }
    }
}