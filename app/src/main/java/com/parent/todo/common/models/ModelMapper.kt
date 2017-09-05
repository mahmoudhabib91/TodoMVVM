package com.parent.todo.common.models

/**
 * Created by mahmoud on 9/4/17.
 */
interface ModelMapper<in From, out To> {
    fun map(from: From) : To
}