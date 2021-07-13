package com.govind.todoz.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.govind.todoz.R

abstract class BaseActivity : AppCompatActivity(){

    protected fun addActivity(cls: Class<*>?, data: Bundle?) {
        val intent = Intent(this, cls)
        if (data != null) {
            intent.putExtras(data)
        }
        startActivity(intent)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        super.setContentView(view, params)
    }

    override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
        super.addContentView(view, params)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        //apiRequests = new ArrayList<>();
        //AppFileUtils.init(this);
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected fun openSideDrawer() {}
    protected fun replaceActivity(cls: Class<*>?, data: Bundle?) {
        addActivity(cls, data)
        finish()
    }

    protected fun popLastFragment() {
        supportFragmentManager.popBackStack()
    }

    protected fun popAllFragments() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }

    protected fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, fragment.javaClass.name)
            .addToBackStack(fragment.javaClass.name)
            .commit()
    }

    protected fun addFragmentWithAnimation(fragment: Fragment?) {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack("image", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentManager.beginTransaction()
            .add(R.id.container, fragment!!)
            .addToBackStack("image")
            .commit()

        /*getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(sharedElement, ViewCompat.getTransitionName(sharedElement))
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();*/
    }

    protected fun replaceFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment!!)
            .commit()
    }

    protected fun replaceFragment(fragment: Fragment?, sharedElement: View?) {
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(sharedElement!!, ViewCompat.getTransitionName(sharedElement)!!)
            .replace(R.id.container, fragment!!)
            .commit()
    }

    protected fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    /*public void refreshMenuItems() {
        if (this.toolbar != null) {
            try {
                Fragment fragment = getTopFragment();
                replaceMenuItems(((BaseFragment) fragment).getMenuItems());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
    /*public void replaceMenuItems(List<View> menuItems) {
        this.toolbar.replaceMenuItems(menuItems);
    }*/
    private fun hasBackStackEntry(): Boolean {
        return try {
            val count = supportFragmentManager.backStackEntryCount
            count > 0
        } catch (e: Exception) {
            true
        }
    }

    protected fun hideKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    /*private void showLoginActivity() {
        startActivity(new Intent(sg.worktable.frontrawnative.activities.BaseActivity.this, AuthActivity.class));
        finish();
    }*/
    interface PermissionHandler {
        fun onPermissionsGranted()
        fun onPermissionsDenied()
    }
}