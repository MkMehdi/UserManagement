package ma.sample.usertasks.ui.users.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_custom.view.*
import ma.sample.usertasks.R
import ma.sample.usertasks.data.entity.User

/**
 * Created by Elmehdi Mellouk on 10/14/20.
 * elmehdi.mellouk@xpi.com
 */
class UsersAdapter(private val listener: Listener) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {


    private val items = ArrayList<User>()

    fun setItems(items: ArrayList<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_custom, parent, false)
        return UsersViewHolder(v)
    }



    override fun onBindViewHolder(
        holder: UsersViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.set(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class UsersViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @SuppressLint("SetTextI18n")
        fun set(item: User?) {
            itemView.textName.text = "${item?.name} (${item?.username})"
            itemView.textEmail.text = item?.email


            itemView.setOnClickListener { listener.onItemClick(item!!) }
        }
    }

    interface Listener {
        fun onItemClick(user:User)
    }

}