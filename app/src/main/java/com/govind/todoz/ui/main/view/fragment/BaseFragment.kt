package com.govind.todoz.ui.main.view.fragment

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    var callback: Callback? = null
    var isViewCreated = false
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = if (context is Callback) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement DashCallback"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)
        bindViews(view)
        view.isClickable = true
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
    }

    fun showToast(@StringRes resId: Int) {
        if (context != null) {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(text: String?) {
        if (context != null) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    @get:LayoutRes
    protected abstract val layout: Int

    open fun refreshFragment() {

    }

    val title: String
        get() = ""


    override fun onDestroy() {
        super.onDestroy()
    }

    protected abstract fun bindViews(root: View?)
    fun notifyNewChatMessageReceived() {
        //Override where required
    }

    fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}