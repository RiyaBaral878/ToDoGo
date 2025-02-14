package com.example.mobileapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvTodoItems = findViewById<RecyclerView>(R.id.rvToDoItems)
        val etTodoTitle = findViewById<EditText>(R.id.etToDoTitle)
        val btnAddTodo = findViewById<Button>(R.id.btnAddToDo)
        val btnDeleteDoneTodos = findViewById<Button>(R.id.btnDeleteToDo)




        toDoAdapter = ToDoAdapter(mutableListOf())


        rvTodoItems.adapter = toDoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                toDoAdapter.addToDo(todo)
                etTodoTitle.text.clear()

            }
        }

btnDeleteDoneTodos.setOnClickListener {
    toDoAdapter.deleteDoneTodos()

}

        }
    }

