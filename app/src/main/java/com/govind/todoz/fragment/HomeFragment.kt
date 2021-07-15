package com.govind.todoz.fragment

import android.os.Bundle
import android.view.View
import com.example.horizontalcalendar.DateItemClickListener
import com.govind.todoz.R
import com.govind.todoz.adapter.TodoAdapter
import com.govind.todoz.databinding.FragmentHomeBinding
import com.govind.todoz.entities.Todo
import com.govind.todoz.utils.DummyDataEngine

class HomeFragment : BaseFragment(), DateItemClickListener, TodoAdapter.TodoListener {

    var total = 0
    var productCount = 6

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
        todoAdapter.addItems(DummyDataEngine.getDummyTodos())
    }

    private fun initView() {
        todoAdapter = TodoAdapter(ArrayList(), this)
        binding.evTodo.adapter = todoAdapter;
        binding.btnAddTodo.setOnClickListener { callback?.onAddTodoRequested() }
    }

    override fun onDateClick(date: String) {
        showToast(date)
    }

    override fun onTodoSelected(selectedTodo: Todo?) {
        showToast(selectedTodo?.title)
    }
}