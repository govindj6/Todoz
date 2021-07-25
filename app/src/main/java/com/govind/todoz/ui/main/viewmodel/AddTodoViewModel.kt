package com.govind.todoz.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.data.repository.TodoRepository
import kotlinx.coroutines.launch

class AddTodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    fun saveTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                todoRepository.save(todo)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}