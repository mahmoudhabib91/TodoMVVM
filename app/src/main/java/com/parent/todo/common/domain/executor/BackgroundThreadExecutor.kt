package tv.niceq8i.app.common.domain.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by mahmoud on 6/4/17.
 */
class BackgroundThreadExecutor : ThreadExecutor {
    override val scheduler: Scheduler = Schedulers.io()
}