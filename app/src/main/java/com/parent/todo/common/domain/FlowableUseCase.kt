package com.parent.todo.common.domain

import io.reactivex.Flowable
import tv.niceq8i.app.common.domain.Params
import tv.niceq8i.app.common.domain.emptyParams
import tv.niceq8i.app.common.domain.executor.PostThreadExecutor
import tv.niceq8i.app.common.domain.executor.ThreadExecutor

/**
 * Created by mahmoud on 9/4/17.
 */
abstract class FlowableUseCase<T> protected constructor(private val threadExecutor: ThreadExecutor
                                                        , private val postThreadExecutor: PostThreadExecutor) {
    protected abstract fun buildUseCaseFlowable(params: Params): Flowable<T>

    fun getFlowable(params: Params = emptyParams()): Flowable<T> =
            buildUseCaseFlowable(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}