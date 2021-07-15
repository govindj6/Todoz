package com.govind.todoz.fragment

import android.os.Bundle
import android.view.View
import com.govind.todoz.R
import com.govind.todoz.databinding.FragmentAddTodoBinding

class AddTodoFragment : BaseFragment() {

    private lateinit var binding: FragmentAddTodoBinding

    override val layout: Int
        get() = R.layout.fragment_add_todo

    override fun bindViews(root: View?) {
        binding = FragmentAddTodoBinding.bind(root!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack.setOnClickListener {
            callback?.onNavigateBackRequested()
        }
    }
}