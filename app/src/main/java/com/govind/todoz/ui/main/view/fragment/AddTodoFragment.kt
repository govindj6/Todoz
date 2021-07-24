package com.govind.todoz.ui.main.view.fragment

import android.os.Bundle
import android.view.View
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.databinding.FragmentAddTodoBinding
import com.govind.todoz.utils.DummyDataEngine

class AddTodoFragment : BaseFragment() {

    private lateinit var binding: FragmentAddTodoBinding

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
    }

    private fun handleSaveButton() {
        val todo = Todo(
            3,
            binding.edtTitle.text.toString(),
            binding.edtDesc.text.toString(),
            "15-07-2021",
            false
        )
        DummyDataEngine.addTodo(todo)
        callback?.onNavigateBackRequested()
        showToast("Saved")
    }
}