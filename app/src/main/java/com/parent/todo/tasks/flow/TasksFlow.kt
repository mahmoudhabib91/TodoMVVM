package com.parent.todo.tasks.flow

import android.content.Context

/**
 * Created by mahmoud on 9/7/17.
 */
interface TasksFlow {
    fun openAddTask(requestCode: Int)

    fun openTaskDetails(taskId: String)
}