package com.parent.todo.tasks.add.domain

import com.parent.todo.common.domain.CompletableUseCase
import com.parent.todo.data.TasksDataSource
import io.reactivex.Completable
import tv.niceq8i.app.common.domain.Params
import tv.niceq8i.app.common.domain.executor.PostThreadExecutor
import tv.niceq8i.app.common.domain.executor.ThreadExecutor

/**
 * Created by mahmoud on 9/5/17.
 */
class AddTaskUseCase(threadExecutor: ThreadExecutor,
                     postThreadExecutor: PostThreadExecutor,
                     private val tasksDataSource: TasksDataSource) :
        CompletableUseCase(threadExecutor, postThreadExecutor) {

    companion object {
        const val PARAM_TITLE = "title"
        const val PARAM_BODY = "body"
        const val PARAM_DUE_DATE = "due_date"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            tasksDataSource.addTask(params.get(PARAM_TITLE),
                    params.get(PARAM_BODY),
                    params.get(PARAM_DUE_DATE))
}