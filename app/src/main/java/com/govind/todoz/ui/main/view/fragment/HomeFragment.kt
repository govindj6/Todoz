package com.govind.todoz.ui.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.horizontalcalendar.DateItemClickListener
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.databinding.FragmentHomeBinding
import com.govind.todoz.ui.main.adapter.TodoAdapter
import com.govind.todoz.utils.DummyDataEngine

class HomeFragment : BaseFragment(), DateItemClickListener, TodoAdapter.TodoListener {

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
    }

    override fun refreshFragment() {
        super.refreshFragment()
        val dummy = DummyDataEngine()
        dummy.addDummyItems()
        showToast("called")
        loadData()
        refreshChildFragment()
    }

    private fun refreshChildFragment() {
        val bf: BaseFragment? = getTopFragment()
        if (bf != null) {
            bf.refreshFragment()
        }
    }

    fun getTopFragment(): BaseFragment? {
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

    private fun loadData() {
        todoAdapter.replaceItems(DummyDataEngine.getDummyTodos())
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