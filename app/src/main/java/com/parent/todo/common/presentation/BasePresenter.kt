package com.parent.todo.common.presentation

/**
 * Created by mahmoud on 9/4/17.
 */
interface BasePresenter {
    fun onCreate()

    fun onResume()

    fun onPause()

    fun onDestroy()
}