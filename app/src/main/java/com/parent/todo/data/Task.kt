package com.parent.todo.data

/**
 * Created by mahmoud on 9/4/17.
 */
data class Task(var id: String = "",
                var title: String = "",
                var body: String = "",
                var createData: Long = 0,
                var dueDate: Long = 0,
                var completed: Boolean = false)