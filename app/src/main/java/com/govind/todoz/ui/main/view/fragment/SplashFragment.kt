package com.govind.todoz.ui.main.view.fragment

import android.os.Bundle
import android.view.View
import com.govind.todoz.R
import com.govind.todoz.data.database.AppDatabase
import com.govind.todoz.data.repository.TodoRepository
import com.govind.todoz.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding

    override val layout: Int
        get() = R.layout.fragment_splash

    override fun bindViews(root: View?) {
        binding = FragmentSplashBinding.bind(root!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SplashThread().start();
    }

    inner class SplashThread : Thread() {
        override fun run() {
            super.run()
            try {
                sleep(3000)
                callback?.showHomeScreen(
                    todoRepository = TodoRepository(
                        AppDatabase.DatabaseBuilder.getInstance(
                            requireContext()
                        )
                    )
                )
                println("Call Dashboard fragment")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}