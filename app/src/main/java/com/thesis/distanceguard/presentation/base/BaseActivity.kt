package com.thesis.distanceguard.presentation.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.thesis.distanceguard.factory.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var fragmentManager: FragmentManager
    protected abstract fun getResLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(getResLayoutId())
    }

    private fun generateFragmentManager() {
        Timber.d("generateFragmentManager")
        fragmentManager = supportFragmentManager
    }

    fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addFragment(fragment: Fragment, tag: String, containerId: Int) {
        Timber.d("addFragment: name=${fragment.javaClass.name}")
        generateFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,0,0,android.R.anim.slide_out_right)
        fragmentTransaction.add(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String, containerId: Int) {
        Timber.d("replaceFragment: name=${fragment.javaClass.name}")
        generateFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,0,0,android.R.anim.slide_out_right)
        fragmentTransaction.replace(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

}