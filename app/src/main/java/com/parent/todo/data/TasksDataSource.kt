package com.parent.todo.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/4/17.
 */
interface TasksDataSource {
    fun loadTasks(): Observable<List<Task>>

    fun loadTask(taskId: String): Single<Task>

    fun addTask(title: String, body: String, dueDate: Long): Completable

    fun deleteTask(taskId: Long): Completable

    fun updateTask(taskId: Long, title: String, body: String, dueDate: Long, completed: Boolean)
            : Completable

    fun setTaskCompleted(taskId: Long, completed: Boolean): Completable
}