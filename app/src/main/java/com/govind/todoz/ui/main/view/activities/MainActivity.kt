package com.govind.todoz.ui.main.view.activities

import android.os.Bundle
import com.govind.todoz.R
import com.govind.todoz.ui.main.view.fragment.AddTodoFragment
import com.govind.todoz.ui.main.view.fragment.Callback
import com.govind.todoz.ui.main.view.fragment.HomeFragment
import com.govind.todoz.ui.main.view.fragment.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSplashScreen()
    }

    private fun showSplashScreen() {
        addFragment(SplashFragment())
    }

    override fun showHomeScreen() {
        replaceFragment(HomeFragment());
    }

    override fun onAddTodoRequested() {
        addFragment(AddTodoFragment())
    }

    override fun onNavigateBackRequested() {
        popLastFragment()
    }
}