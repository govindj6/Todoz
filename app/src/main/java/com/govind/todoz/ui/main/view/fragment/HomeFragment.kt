package com.govind.todoz.ui.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.horizontalcalendar.DateItemClickListener
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.data.repository.TodoRepository
import com.govind.todoz.databinding.FragmentHomeBinding
import com.govind.todoz.ui.base.ViewModelFactory
import com.govind.todoz.ui.main.adapter.TodoAdapter
import com.govind.todoz.ui.main.viewmodel.HomeViewModel

class HomeFragment(private val todoRepository: TodoRepository) : BaseFragment(),
    DateItemClickListener, TodoAdapter.TodoListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
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
        setupViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.getAllTodos().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                todoAdapter.replaceItems(it)
            } else {
                showToast("Todo not found")
            }
        })
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(this, ViewModelFactory(todoRepository)).get(HomeViewModel::class.java)
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
        binding.btnAddTodo.setOnClickListener { callback?.onAddTodoRequested(todoRepository) }
    }

    override fun onDateClick(date: String) {
        println(date)
        viewModel.getTodosByDate(date)?.observe(viewLifecycleOwner, Observer {
            todoAdapter.replaceItems(it)
        })
    }

    override fun onTodoSelected(selectedTodo: Todo?) {
        showToast(selectedTodo?.title)
    }
}