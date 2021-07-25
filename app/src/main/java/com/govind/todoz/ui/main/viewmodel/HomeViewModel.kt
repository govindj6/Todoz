package com.govind.todoz.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.data.repository.TodoRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private lateinit var todos: LiveData<List<Todo>>

    init {
        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            try {
                todos = todoRepository.getAll()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun getAllTodos(): LiveData<List<Todo>> {
        return todos
    }
}