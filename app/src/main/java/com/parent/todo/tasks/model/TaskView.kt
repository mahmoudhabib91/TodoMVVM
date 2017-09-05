package com.parent.todo.tasks.model

/**
 * Created by mahmoud on 9/4/17.
 */
data class TaskView(var id: String = "",
                    var title: String = "",
                    var body: String = "",
                    var createDate: Long = 0,
                    var dueDate: Long = 0,
                    var completed: Boolean = false)