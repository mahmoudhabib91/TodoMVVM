package com.parent.todo.tasks.add.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.parent.todo.common.ui.BaseActivity

/**
 * Created by mahmoud on 9/5/17.
 */
class AddTaskActivity : BaseActivity() {
    companion object {
        fun startIntent(context: Context) = Intent(context, AddTaskActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}