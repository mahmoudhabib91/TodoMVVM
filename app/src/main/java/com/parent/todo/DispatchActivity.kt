package com.parent.todo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.parent.todo.tasks.list.presentation.TasksListActivity
import org.jetbrains.anko.startActivity

class DispatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<TasksListActivity>()
        finish()
    }
}
