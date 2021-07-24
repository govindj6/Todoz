package com.govind.todoz.ui.main.view.fragment

interface Callback {
    fun showHomeScreen()
    fun onAddTodoRequested()
    fun onNavigateBackRequested()
}