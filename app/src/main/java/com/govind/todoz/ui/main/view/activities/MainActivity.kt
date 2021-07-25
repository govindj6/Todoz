package com.govind.todoz.ui.main.view.activities

import android.os.Bundle
import com.govind.todoz.R
import com.govind.todoz.data.repository.TodoRepository
import com.govind.todoz.ui.main.view.fragment.AddTodoFragment
import com.govind.todoz.ui.main.view.fragment.Callback
import com.govind.todoz.ui.main.view.fragment.HomeFragment
import com.govind.todoz.ui.main.view.fragment.SplashFragment

class MainActivity : BaseActivity(), Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSplashScreen()
    }

    private fun showSplashScreen() {
        addFragment(SplashFragment())
    }

    override fun showHomeScreen(todoRepository: TodoRepository) {
        replaceFragment(HomeFragment(todoRepository));
    }

    override fun onAddTodoRequested(todoRepository: TodoRepository) {
        addFragment(AddTodoFragment(todoRepository))
    }

    override fun onNavigateBackRequested() {
        popLastFragment()
    }
}