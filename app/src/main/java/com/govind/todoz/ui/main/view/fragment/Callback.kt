package com.govind.todoz.ui.main.view.fragment

import com.govind.todoz.data.repository.TodoRepository

interface Callback {
    fun showHomeScreen(todoRepository: TodoRepository)
    fun onAddTodoRequested(todoRepository: TodoRepository)
    fun onNavigateBackRequested()
}