package tv.niceq8i.app.common.domain

import io.reactivex.Observable
import tv.niceq8i.app.common.domain.executor.PostThreadExecutor
import tv.niceq8i.app.common.domain.executor.ThreadExecutor

/**
 * Created by mahmoud on 6/1/17.
 */
abstract class ObservableUseCase<T> protected constructor(private val threadExecutor: ThreadExecutor
                                                          , private val postThreadExecutor: PostThreadExecutor) {
    protected abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun getObservable(params: Params = emptyParams()): Observable<T> =
            buildUseCaseObservable(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}