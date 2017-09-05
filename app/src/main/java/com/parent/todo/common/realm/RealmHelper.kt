package com.parent.todo.common.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by mahmoud on 9/4/17.
 */
object RealmHelper {
    const val NAME = "tasks.realm"
    const val VERSION = 0L

    fun initRealm(context: Context) {
        Realm.init(context)

        Realm.setDefaultConfiguration(getDefaultConfiguration())
    }

    fun getDefaultConfiguration() =
            RealmConfiguration.Builder()
                    .name(NAME)
                    .schemaVersion(VERSION)
                    .build()
}