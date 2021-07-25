package com.govind.todoz.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.databinding.ItemTodoBinding

class TodoAdapter(var todoList: MutableList<Todo>, var listener: TodoListener) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_todo, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun replaceItems(newItems: List<Todo>?) {
        todoList.clear()
        todoList.addAll(newItems!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        todoList.clear()
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Todo>) {
        val previousCount = todoList.size
        todoList.addAll(newItems)
        notifyItemRangeInserted(previousCount, newItems.size)
    }

    inner class ViewHolder(var rootView: View) : RecyclerView.ViewHolder(rootView) {
        private val binding: ItemTodoBinding = ItemTodoBinding.bind(itemView)
        fun bind(todo: Todo?) {
            println(todo.toString())
            binding.txtTitle.text = todo?.title
            rootView.setOnClickListener {
                listener.onTodoSelected(todo)
            }
        }
    }

    interface TodoListener {
        fun onTodoSelected(selectedTodo: Todo?)
    }
}