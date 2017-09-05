package com.parent.todo.tasks.list.presentation

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.parent.todo.common.models.ModelMapper
import com.parent.todo.data.Task
import com.parent.todo.tasks.model.TaskView
import org.jetbrains.anko.AnkoLogger
import tv.niceq8i.app.common.domain.ObservableUseCase

/**
 * Created by mahmoud on 9/4/17.
 */
class TasksListViewModelFactory(private val application: Application,
                                private val loadTasksUseCase: ObservableUseCase<List<Task>>,
                                private val modelMapper: ModelMapper<Task, TaskView>) :
        ViewModelProvider.Factory, AnkoLogger {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        if (modelClass?.isAssignableFrom(TasksListViewModel::class.java) ?: false) {
            return TasksListViewModel(application, loadTasksUseCase, modelMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}