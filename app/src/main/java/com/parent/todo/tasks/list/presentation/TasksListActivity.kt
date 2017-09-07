package com.parent.todo.tasks.list.presentation

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.parent.todo.R
import com.parent.todo.common.ui.BaseActivity
import com.parent.todo.data.source.TasksRepository
import com.parent.todo.tasks.flow.TasksFlow
import com.parent.todo.tasks.flow.TasksFlowController
import com.parent.todo.tasks.list.domain.LoadTasksUseCase
import com.parent.todo.tasks.model.TaskViewModelMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_tasks_list.*
import org.jetbrains.anko.toast
import tv.niceq8i.app.common.domain.executor.BackgroundThreadExecutor
import tv.niceq8i.app.common.domain.executor.UiThreadExecutor

/**
 * Created by mahmoud on 9/4/17.
 */
class TasksListActivity : BaseActivity() {
    private val RC_ADD_TASK = 0

    private lateinit var viewModel: TasksListViewModel
    private lateinit var tasksFlow: TasksFlow
    private val tasksListAdapter = TasksListAdapter(mutableListOf())
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_list)

        val factory = TasksListViewModelFactory(this.application,
                LoadTasksUseCase(BackgroundThreadExecutor(),
                        UiThreadExecutor(), TasksRepository), TaskViewModelMapper())

        viewModel = ViewModelProviders.of(this, factory).get(TasksListViewModel::class.java)

        tasksFlow = TasksFlowController(this)

        tasks_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = tasksListAdapter
        }

        add_task_fab.setOnClickListener {
            viewModel.addTask()
        }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(viewModel.listObservable.subscribeBy({
            tasksListAdapter.setNewData(it)
        }))

        compositeDisposable.add(viewModel.isLoadingVisibleObservable.subscribeBy({
            val progressVisibility = if (it) View.VISIBLE else View.GONE
            loading_progress.visibility = progressVisibility

            val listVisibility = if (it) View.GONE else View.VISIBLE
            tasks_recycler_view.visibility = listVisibility
        }))

        compositeDisposable.add(viewModel.loadingErrorObservable.subscribeBy({
            toast(it)
        }))

        compositeDisposable.add(viewModel.showAddTaskObservable.subscribe {
            if (it) {
                tasksFlow.openAddTask(RC_ADD_TASK)
            }
        })

        viewModel.start()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}