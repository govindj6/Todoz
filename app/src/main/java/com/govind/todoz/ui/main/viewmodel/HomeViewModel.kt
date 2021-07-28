package com.govind.todoz.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    private lateinit var todos: LiveData<List<Todo>>
    private lateinit var filterTodoList: LiveData<List<Todo>>

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

    fun getTodosByDate(date: String): LiveData<List<Todo>>? {
        viewModelScope.async {
            try {
                filterTodoList = todoRepository.getTodosByDate(date)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
        return filterTodoList
    }

    fun deleteAllTodos() {
        viewModelScope.launch {
            try {
                todoRepository.deleteAll()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            try {
                todoRepository.delete(todo)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun getAllTodos(): LiveData<List<Todo>> {
        return todos
    }
}