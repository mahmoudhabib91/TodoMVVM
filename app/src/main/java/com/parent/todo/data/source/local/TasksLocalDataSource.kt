package com.parent.todo.data.source.local

import com.parent.todo.common.models.ModelMapper
import com.parent.todo.data.Task
import com.parent.todo.data.TasksDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import io.realm.Sort

/**
 * Created by mahmoud on 9/4/17.
 */
object TasksLocalDataSource : TasksDataSource {

    private val modelMapper: ModelMapper<TaskRealm, Task> = TaskRealmModelMapper()

    override fun loadTasks(): Observable<List<Task>> =
            Observable.create { e ->
                val realm = Realm.getDefaultInstance()

                e.setCancellable {
                    realm.close()
                }

                val tasks = realm.where(TaskRealm::class.java)
                        .findAllSorted("createDate", Sort.DESCENDING)
                        .map { mapTask(it) }

                if (tasks.isEmpty()) {
                    if (!e.isDisposed) {
                        e.onError(NoSuchElementException("No tasks were found"))
                    }
                } else {
                    if (!e.isDisposed) {
                        e.onNext(tasks)
                    }
                }
            }

    override fun loadTask(taskId: String): Single<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTask(title: String, body: String, dueDate: Long): Completable =
            Completable.create {
                val realm = Realm.getDefaultInstance()

                it.setCancellable {
                    if (realm.isInTransaction) {
                        realm.cancelTransaction()
                    }

                    realm.close()
                }

                realm.beginTransaction()

                val task = realm.createObject(TaskRealm::class.java)
                task.title = title
                task.body = body
                task.dueDate = dueDate

                realm.commitTransaction()

                if (!it.isDisposed) {
                    it.onComplete()
                }
            }

    override fun deleteTask(taskId: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTask(taskId: Long, title: String, body: String, dueDate: Long, completed: Boolean): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTaskCompleted(taskId: Long, completed: Boolean): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapTask(taskRealm: TaskRealm): Task = modelMapper.map(taskRealm)
}