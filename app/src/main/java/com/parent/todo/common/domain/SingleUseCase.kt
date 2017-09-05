package com.parent.todo.common.domain

import io.reactivex.Single
import tv.niceq8i.app.common.domain.Params
import tv.niceq8i.app.common.domain.emptyParams
import tv.niceq8i.app.common.domain.executor.PostThreadExecutor
import tv.niceq8i.app.common.domain.executor.ThreadExecutor

/**
 * Created by mahmoud on 9/4/17.
 */
abstract class SingleUseCase<T> protected constructor(private val threadExecutor: ThreadExecutor
                                                      , private val postThreadExecutor: PostThreadExecutor) {
    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    fun getSingle(params: Params = emptyParams()): Single<T> =
            buildUseCaseSingle(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}