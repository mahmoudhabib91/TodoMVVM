package com.parent.todo.common.presentation

/**
 * Created by mahmoud on 9/4/17.
 */
interface BaseView<P : BasePresenter> {
    var presenter: P?

    fun setLoadinIndicatorVisible(visible: Boolean)

    fun setContentVisible(visible: Boolean)
}