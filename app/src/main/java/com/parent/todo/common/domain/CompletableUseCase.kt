package com.parent.todo.common.domain

import io.reactivex.Completable
import tv.niceq8i.app.common.domain.Params
import tv.niceq8i.app.common.domain.emptyParams
import tv.niceq8i.app.common.domain.executor.PostThreadExecutor
import tv.niceq8i.app.common.domain.executor.ThreadExecutor

/**
 * Created by mahmoud on 9/4/17.
 */
abstract class CompletableUseCase protected constructor(private val threadExecutor:
                                                        ThreadExecutor
                                                        , private val postThreadExecutor: PostThreadExecutor) {
    protected abstract fun buildUseCaseCompletable(params: Params): Completable

    fun getCompletable(params: Params = emptyParams()): Completable =
            buildUseCaseCompletable(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}