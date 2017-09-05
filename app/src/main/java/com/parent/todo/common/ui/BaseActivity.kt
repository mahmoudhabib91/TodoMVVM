package com.parent.todo.common.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.parent.todo.R

/**
 * Created by mahmoud on 9/4/17.
 */
abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {
    private val lifeCycleRegistry = LifecycleRegistry(this)

    private var toolbar: Toolbar? = null

    private var isBackEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initToolbar()
    }

    override fun getLifecycle(): Lifecycle = lifeCycleRegistry

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (!onUpClick()) {
                    onBackPressed()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    open protected fun onUpClick(): Boolean = false

    private fun initToolbar() {
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.apply {
            title = ""
            setSupportActionBar(this)
        }

        supportActionBar?.apply {
            updateAppBar(this)
        }
    }

    fun setBackEnabled(enabled: Boolean) {
        isBackEnabled = enabled
        supportActionBar?.apply {
            updateAppBar(this)
        }
    }

    private fun updateAppBar(actionBar: ActionBar) {
        actionBar.setDisplayHomeAsUpEnabled(isBackEnabled)
    }
}
