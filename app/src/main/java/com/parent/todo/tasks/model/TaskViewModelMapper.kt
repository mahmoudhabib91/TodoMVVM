package com.parent.todo.tasks.model

import com.parent.todo.common.models.ModelMapper
import com.parent.todo.data.Task
import com.parent.todo.tasks.model.TaskView

/**
 * Created by mahmoud on 9/5/17.
 */
class TaskViewModelMapper : ModelMapper<Task, TaskView> {
    override fun map(from: Task): TaskView {
        val taskView = TaskView()
        taskView.id = from.id
        taskView.title = from.title
        taskView.body = from.body
        taskView.createDate = from.createData
        taskView.dueDate = from.dueDate
        taskView.completed = from.completed
        return taskView
    }
}