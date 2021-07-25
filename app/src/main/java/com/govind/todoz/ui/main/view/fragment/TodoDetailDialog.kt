package com.govind.todoz.ui.main.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.govind.todoz.R
import com.govind.todoz.data.modal.Todo
import com.govind.todoz.databinding.DialogTodoDetailBinding

class TodoDetailDialog : DialogFragment() {

    private lateinit var binding: DialogTodoDetailBinding
    private lateinit var todo: Todo

    companion object {
        const val TAG = "TodoDetailDialog"
        private const val KEY_TODO = "todo"

        fun newInstance(todo: Todo?, isCancelable: Boolean): TodoDetailDialog {
            val args = Bundle()
            args.putSerializable(KEY_TODO, todo)
            val fragment = TodoDetailDialog()
            fragment.isCancelable = isCancelable
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            todo = arguments?.getSerializable(KEY_TODO) as Todo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_todo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogTodoDetailBinding.bind(view)
        binding.edtTitle.setText(todo.title)
        binding.edtDesc.setText(todo.desc)
        binding.btnOk.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val window: Window? = dialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}