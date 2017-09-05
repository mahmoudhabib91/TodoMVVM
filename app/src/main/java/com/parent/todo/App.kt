package com.parent.todo

import android.app.Application
import com.parent.todo.common.realm.RealmHelper

/**
 * Created by mahmoud on 9/4/17.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        RealmHelper.initRealm(this)
    }
}