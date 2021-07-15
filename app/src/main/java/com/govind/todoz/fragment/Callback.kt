package com.govind.todoz.fragment

interface Callback {
    fun showHomeScreen()
    fun onAddTodoRequested()
    fun onNavigateBackRequested()
}