package tv.niceq8i.app.common.domain.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by mahmoud on 6/4/17.
 */
class UiThreadExecutor : PostThreadExecutor {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}