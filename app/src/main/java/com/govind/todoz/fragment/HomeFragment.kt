package com.govind.todoz.fragment

import android.os.Bundle
import android.view.View
import com.example.horizontalcalendar.DateItemClickListener
import com.govind.todoz.R
import com.govind.todoz.adapter.TodoAdapter
import com.govind.todoz.databinding.FragmentHomeBinding
import com.govind.todoz.entities.Todo
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment(),DateItemClickListener, TodoAdapter.TodoListener{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var todoAdapter: TodoAdapter

    override val layout: Int
        get() = R.layout.fragment_home

    override fun bindViews(root: View?) {
        binding = FragmentHomeBinding.bind(root!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendar.initialize(this)
        initView()
        loadData()
    }

    private fun loadData() {
        todoAdapter.addItems(getDummyTodos())
    }

    private fun initView() {
        todoAdapter = TodoAdapter(ArrayList(), this)
        binding.evTodo.adapter = todoAdapter;
    }

    override fun onDateClick(date: String) {
        showToast(date)
    }

    override fun onTodoSelected(selectedTodo: Todo?) {
        showToast(selectedTodo?.title)
    }

    fun getDummyTodos() : MutableList<Todo>{
        var todos: MutableList<Todo> = ArrayList()
        todos.add(Todo(1,"Grocory", "Handwash, Facewash", "02-07-2021",false))
        todos.add(Todo(2,"Stock Watchlist", "Infosys, Wipro, CDSL, Lauras lab","02-07-2021", false))
        return todos
    }
}