package tv.niceq8i.app.common.domain.executor

import io.reactivex.Scheduler

/**
 * Created by mahmoud on 6/1/17.
 */
interface ThreadExecutor {
    val scheduler: Scheduler
}