package com.parent.todo.data.source.local

import com.parent.todo.common.models.ModelMapper
import com.parent.todo.data.Task

/**
 * Created by mahmoud on 9/4/17.
 */
class TaskRealmModelMapper : ModelMapper<TaskRealm, Task> {
    override fun map(from: TaskRealm): Task {
        val task = Task()
        task.id = from.id
        task.title = from.title
        task.body = from.body
        task.createData = from.createDate
        task.dueDate = from.dueDate
        task.completed = from.completed
        return task
    }
}