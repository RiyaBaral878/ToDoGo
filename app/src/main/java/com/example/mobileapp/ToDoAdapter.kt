package com.example.mobileapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvToDoTitle: TextView = itemView.findViewById(R.id.tvToDoTitle)
        val cbDone: CheckBox = itemView.findViewById(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemtodo,
            parent,
            false
        )
        return TodoViewHolder(itemView)
    }

    fun addToDo(todo : Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    fun deleteDoneTodos(){
       todos.removeAll{
           todo ->  todo.isChecked
       }
        notifyDataSetChanged()
    }
    private  fun toggleStrikeThrough(tvToDoTitle : TextView, isChecked : Boolean){
        if(isChecked) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        }else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.tvToDoTitle.text = curTodo.title
        holder.cbDone.isChecked = curTodo.isChecked
        toggleStrikeThrough(holder.tvToDoTitle , curTodo.isChecked)
        holder.cbDone.setOnCheckedChangeListener{_, isChecked ->
            toggleStrikeThrough(holder.tvToDoTitle , isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
