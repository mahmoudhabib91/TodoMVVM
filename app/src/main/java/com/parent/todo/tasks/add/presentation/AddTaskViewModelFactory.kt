package com.parent.todo.tasks.add.presentation

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.parent.todo.common.domain.CompletableUseCase

/**
 * Created by mahmoud on 9/5/17.
 */
class AddTaskViewModelFactory(private val application: Application,
                              private val addTaskUseCase: CompletableUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        if (modelClass?.isAssignableFrom(AddTaskViewModel::class.java) ?: false) {
            return AddTaskViewModel(application, addTaskUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}