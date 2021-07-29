package com.govind.todoz.ui.main.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.databinding.FragmentAddTodoBinding
import com.govind.todoz.ui.main.viewmodel.AddTodoViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddTodoFragment : BaseFragment() {

    private val DATE_FORMAT: String = "dd-MM-yyyy"
    private lateinit var binding: FragmentAddTodoBinding
    val viewModel: AddTodoViewModel by viewModels()

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

    @SuppressLint("SimpleDateFormat")
    private fun handleSaveButton() {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT)
        val currentDate: String = simpleDateFormat.format(Date())
        val todo = Todo(
            binding.edtTitle.text.toString(),
            binding.edtDesc.text.toString(),
            currentDate,
            false
        )
        viewModel.saveTodo(todo)
        Log.d("Saved Entry", todo.toString())
        callback?.onNavigateBackRequested()
        showToast("Saved")
    }
}