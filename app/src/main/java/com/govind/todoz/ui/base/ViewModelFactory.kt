package com.govind.todoz.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.govind.todoz.data.repository.TodoRepository
import com.govind.todoz.ui.main.viewmodel.AddTodoViewModel
import com.govind.todoz.ui.main.viewmodel.HomeViewModel

class ViewModelFactory(private val todoRepository: TodoRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(todoRepository) as T
        }
        if (modelClass.isAssignableFrom(AddTodoViewModel::class.java)) {
            return AddTodoViewModel(todoRepository) as T
        }
        throw IllegalArgumentException("Unable to create view model")
    }
}