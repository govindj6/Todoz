package com.govind.todoz.ui.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.data.repository.TodoRepository
import com.govind.todoz.databinding.FragmentAddTodoBinding
import com.govind.todoz.ui.base.ViewModelFactory
import com.govind.todoz.ui.main.viewmodel.AddTodoViewModel

class AddTodoFragment(private val todoRepository: TodoRepository) : BaseFragment() {

    private lateinit var binding: FragmentAddTodoBinding
    private lateinit var viewModel: AddTodoViewModel

    override val layout: Int
        get() = R.layout.fragment_add_todo

    override fun bindViews(root: View?) {
        binding = FragmentAddTodoBinding.bind(root!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack.setOnClickListener {
            callback?.onNavigateBackRequested()
        }

        binding.btnSave.setOnClickListener {
            handleSaveButton()
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(todoRepository)
            ).get(AddTodoViewModel::class.java)
    }

    private fun handleSaveButton() {
        val todo = Todo(
            binding.edtTitle.text.toString(),
            binding.edtDesc.text.toString(),
            "15-07-2021",
            false
        )
        viewModel.saveTodo(todo)
        callback?.onNavigateBackRequested()
        showToast("Saved")
    }
}