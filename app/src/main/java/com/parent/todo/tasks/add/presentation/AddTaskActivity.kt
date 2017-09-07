package com.parent.todo.tasks.add.presentation

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.parent.todo.R
import com.parent.todo.common.ui.BaseActivity
import com.parent.todo.data.source.TasksRepository
import com.parent.todo.tasks.add.domain.AddTaskUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_add_task.*
import org.jetbrains.anko.toast
import tv.niceq8i.app.common.domain.executor.BackgroundThreadExecutor
import tv.niceq8i.app.common.domain.executor.UiThreadExecutor

/**
 * Created by mahmoud on 9/5/17.
 */
class AddTaskActivity : BaseActivity() {
    private lateinit var addTaskViewModel: AddTaskViewModel
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.add_task_activity_title)
        setContentView(R.layout.activity_add_task)

        setBackEnabled(true)

        val factory = AddTaskViewModelFactory(this.application,
                AddTaskUseCase(BackgroundThreadExecutor(),
                        UiThreadExecutor(),
                        TasksRepository))

        addTaskViewModel = ViewModelProviders.of(this, factory).get(AddTaskViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_task, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add -> {
                onAddClick()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(addTaskViewModel.addSuccessCompletable.subscribe {
            setResult(Activity.RESULT_OK)
            finish()
        })

        compositeDisposable.add(addTaskViewModel.addTaskErrorObservable.subscribe {
            toast(it)
        })

        compositeDisposable.add(addTaskViewModel.loadingVisibleObservable.subscribe {
            val progressVisibility = if (it) View.VISIBLE else View.GONE
            loading_progress.visibility = progressVisibility
        })
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun onAddClick() {
        val title = title_edit_text.text.toString()
        val body = body_edit_text.text.toString()

        addTaskViewModel.addTask(title, body, System.currentTimeMillis())
    }
}