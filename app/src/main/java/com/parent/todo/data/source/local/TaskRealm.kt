package com.parent.todo.data.source.local

import io.realm.RealmObject
import java.util.*

/**
 * Created by mahmoud on 9/4/17.
 */
open class TaskRealm(var id: String = UUID.randomUUID().toString(),
                var title: String = "",
                var body: String = "",
                var createDate: Long = System.currentTimeMillis(),
                var dueDate: Long = 0,
                var completed: Boolean = false) : RealmObject()