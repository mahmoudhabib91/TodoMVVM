package com.parent.todo.tasks.list.domain

import com.parent.todo.data.Task
import com.parent.todo.data.TasksDataSource
import io.reactivex.Observable
import tv.niceq8i.app.common.domain.ObservableUseCase
import tv.niceq8i.app.common.domain.Params
import tv.niceq8i.app.common.domain.executor.PostThreadExecutor
import tv.niceq8i.app.common.domain.executor.ThreadExecutor

/**
 * Created by mahmoud on 9/5/17.
 */
class LoadTasksUseCase(threadExecutor: ThreadExecutor,
                       postThreadExecutor: PostThreadExecutor,
                       private val tasksDataSource: TasksDataSource) :
        ObservableUseCase<List<Task>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseObservable(params: Params): Observable<List<Task>> =
            tasksDataSource.loadTasks()
}