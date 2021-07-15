package com.govind.todoz.activities

import android.os.Bundle
import com.govind.todoz.R
import com.govind.todoz.fragment.AddTodoFragment
import com.govind.todoz.fragment.Callback
import com.govind.todoz.fragment.HomeFragment
import com.govind.todoz.fragment.SplashFragment

class MainActivity : BaseActivity(), Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSplashScreen()
    }

    private fun showSplashScreen() {
        replaceFragment(SplashFragment())
    }

    override fun showHomeScreen() {
        addFragment(HomeFragment());
    }

    override fun onAddTodoRequested() {
        addFragment(AddTodoFragment())
    }

    override fun onNavigateBackRequested() {
        popLastFragment()
    }
}