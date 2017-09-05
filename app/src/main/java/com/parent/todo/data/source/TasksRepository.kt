package com.parent.todo.data.source

import com.parent.todo.data.Task
import com.parent.todo.data.TasksDataSource
import com.parent.todo.data.source.local.TasksLocalDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/4/17.
 */
object TasksRepository : TasksDataSource {
    private val localDataSource = TasksLocalDataSource

    override fun loadTasks(): Observable<List<Task>> =
            Observable.fromCallable {
                listOf(Task("0", "Task0", "Dummy body 0", System
                        .currentTimeMillis(), System.currentTimeMillis() + 5000,
                        false), Task("1", "Task1", "Dummy body 1", System
                        .currentTimeMillis(), System.currentTimeMillis() + 5000,
                        false))
            }

    override fun loadTask(taskId: String): Single<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTask(title: String, body: String, dueDate: Long): Completable =
            localDataSource.addTask(title, body, dueDate)

    override fun deleteTask(taskId: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTask(taskId: Long, title: String, body: String, dueDate: Long, completed: Boolean): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTaskCompleted(taskId: Long, completed: Boolean): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}