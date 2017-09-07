package com.parent.todo.tasks.flow

import android.app.Activity
import com.parent.todo.tasks.add.presentation.AddTaskActivity
import org.jetbrains.anko.startActivityForResult

/**
 * Created by mahmoud on 9/7/17.
 */
class TasksFlowController(private val activity: Activity) : TasksFlow {
    override fun openAddTask(requestCode: Int) {
        activity.startActivityForResult<AddTaskActivity>(requestCode)
    }

    override fun openTaskDetails(taskId: String) {
    }
}