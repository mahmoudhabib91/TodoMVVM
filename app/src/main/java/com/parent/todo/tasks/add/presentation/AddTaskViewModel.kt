package com.parent.todo.tasks.add.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.parent.todo.R
import com.parent.todo.common.domain.CompletableUseCase
import com.parent.todo.tasks.add.domain.AddTaskUseCase
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.PublishSubject
import tv.niceq8i.app.common.domain.Params

/**
 * Created by mahmoud on 9/5/17.
 */
class AddTaskViewModel(application: Application,
                       private val addTaskUseCase: CompletableUseCase) : AndroidViewModel(application) {

    val addSuccessCompletable = CompletableSubject.create()

    val loadingVisibleObservable = PublishSubject.create<Boolean>()

    val addTaskErrorObservable = PublishSubject.create<String>()

    fun start() {
    }

    fun addTask(title: String, body: String, dueDate: Long) {
        loadingVisibleObservable.onNext(true)
        addTaskUseCase.getCompletable(Params(AddTaskUseCase.PARAM_TITLE to title,
                AddTaskUseCase.PARAM_BODY to body,
                AddTaskUseCase.PARAM_DUE_DATE to dueDate))
                .subscribeBy(onComplete = {
                    loadingVisibleObservable.onNext(false)
                    addSuccessCompletable.onComplete()
                }, onError = {
                    loadingVisibleObservable.onNext(false)
                    addTaskErrorObservable.onNext(it.message ?:
                            getApplication<Application>().getString(R.string.unknown_error_message))
                })
    }
}