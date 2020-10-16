package ma.sample.usertasks.ui.tasks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_custom.view.*


import ma.sample.usertasks.R
import ma.sample.usertasks.data.entity.Task
import ma.sample.usertasks.data.entity.User

/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */
class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    private val items = ArrayList<Task>()

    fun setItems(items: ArrayList<Task>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): TasksViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_custom, parent, false)
        return TasksViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: TasksViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.set(item)
    }

    override fun getItemCount(): Int {
        return items.size ?: 0
    }

    inner class TasksViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @SuppressLint("SetTextI18n")
        fun set(item: Task?) {
            itemView.textTitle.text = item?.title
            itemView.textStatus.text = if(item?.completed!!) "Completed" else "Not Completed"

        }
    }


}