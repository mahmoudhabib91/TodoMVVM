package com.parent.todo.tasks.list.presentation

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.parent.todo.R
import com.parent.todo.tasks.model.TaskView

/**
 * Created by mahmoud on 9/5/17.
 */
class TasksListAdapter(data: MutableList<TaskView>?) :
        BaseQuickAdapter<TaskView, BaseViewHolder>(R.layout.layout_task_list_item, data) {
    override fun convert(helper: BaseViewHolder?, item: TaskView?) {
        if (helper != null && item != null) {
            helper.setText(R.id.title_text_view, item.title)
            helper.setText(R.id.body_text_view, item.body)
        }
    }
}