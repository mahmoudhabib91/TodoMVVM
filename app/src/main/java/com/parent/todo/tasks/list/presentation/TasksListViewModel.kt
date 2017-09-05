package com.parent.todo.tasks.list.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.parent.todo.R
import com.parent.todo.common.models.ModelMapper
import com.parent.todo.data.Task
import com.parent.todo.tasks.model.TaskView
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import tv.niceq8i.app.common.domain.ObservableUseCase
import kotlin.properties.Delegates

/**
 * Created by mahmoud on 9/4/17.
 */
class TasksListViewModel(application: Application,
                         val loadTasksUseCase: ObservableUseCase<List<Task>>,
                         val modelMapper: ModelMapper<Task, TaskView>) : AndroidViewModel(application), AnkoLogger {

    val listObservable: PublishSubject<List<TaskView>> = PublishSubject.create()
    val isLoadingVisibleObservable: PublishSubject<Boolean> = PublishSubject.create()
    val isEmptyObservable: PublishSubject<Boolean> = PublishSubject.create()
    val loadingErrorObservable: PublishSubject<String> = PublishSubject.create()
    val showAddTaskObservable: PublishSubject<Boolean> = PublishSubject.create()


    private var list: List<TaskView> by Delegates.observable(emptyList<TaskView>()) {
        _, _, newValue ->
        listObservable.onNext(newValue)
    }
    private var isLoadingVisible: Boolean by Delegates.observable(false) {
        _, _, newValue ->
        isLoadingVisibleObservable.onNext(newValue)
    }

    private var isEmpty: Boolean by Delegates.observable(false) {
        _, _, newValue ->
        isEmptyObservable.onNext(newValue)
    }

    init {
        debug("init")
    }

    fun start() {
        loadTasks()
    }

    fun loadTasks() {
        isLoadingVisible = true
        loadTasksUseCase.getObservable().flatMap {
            mapTasks(it)
        }.subscribeBy(onNext = {
            list = it
            isLoadingVisible = false
        }, onError = {
            isLoadingVisible = false
            error("Error loading tasks", it)
            if (it is NoSuchElementException) {
                isEmpty = true
            } else {
                loadingErrorObservable.onNext(it.message ?:
                        getApplication<Application>().getString(R.string.unknown_error_message))
            }
        })
    }

    fun addTask() {
        showAddTaskObservable.onNext(true)
    }

    private fun mapTasks(tasks: List<Task>): Observable<List<TaskView>> =
            Observable.fromCallable {
                tasks.map { modelMapper.map(it) }
            }
}