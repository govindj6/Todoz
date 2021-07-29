package com.govind.todoz.ui.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.horizontalcalendar.DateItemClickListener
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.databinding.FragmentHomeBinding
import com.govind.todoz.ui.main.adapter.TodoAdapter
import com.govind.todoz.ui.main.viewmodel.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment() : BaseFragment(),
    DateItemClickListener, TodoAdapter.TodoListener {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override val layout: Int
        get() = R.layout.fragment_home

    override fun bindViews(root: View?) {
        binding = FragmentHomeBinding.bind(root!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendar.initialize(this)
        binding.txtDateFilter.setOnClickListener { clearDateFilter() }
        binding.txtTitle.text = getGreetingMessage()
        initView()
        setUpObserver()
    }

    private fun clearDateFilter() {
        setUpObserver()
        binding.txtDateFilter.visibility = View.GONE
    }

    private fun setUpObserver() {
        viewModel.getAllTodos().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                todoAdapter.replaceItems(it.asReversed())
            } else {
                showToast("Todo not found")
            }
        })
    }

    override fun refreshFragment() {
        super.refreshFragment()
        refreshChildFragment()
    }

    private fun refreshChildFragment() {
        val bf: BaseFragment? = getTopFragment()
        bf?.refreshFragment()
    }

    private fun getTopFragment(): BaseFragment? {
        val fragentList = childFragmentManager.fragments
        var top: Fragment? = null
        for (i in fragentList.indices.reversed()) {
            top = fragentList[i]
            if (top != null && top is BaseFragment) {
                return top
            }
        }
        return null
    }

    private fun initView() {
        todoAdapter = TodoAdapter(ArrayList(), this)
        binding.evTodo.adapter = todoAdapter;
        binding.btnAddTodo.setOnClickListener { callback?.onAddTodoRequested() }
    }

    override fun onDateClick(date: String) {
        viewModel.getTodosByDate(date)?.observe(viewLifecycleOwner, Observer {
            todoAdapter.replaceItems(it.asReversed())
        })
        binding.txtDateFilter.visibility = View.VISIBLE
    }

    override fun onTodoSelected(selectedTodo: Todo?) {
        TodoDetailDialog.newInstance(selectedTodo, false)
            .show(childFragmentManager, TodoDetailDialog.TAG)
    }

    fun getGreetingMessage(): String {
        val c = Calendar.getInstance()
        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            in 21..23 -> "Good Night"
            else -> "Hello"
        }
    }
}