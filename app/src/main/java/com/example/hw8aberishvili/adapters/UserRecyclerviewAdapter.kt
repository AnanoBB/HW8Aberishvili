package com.example.hw8aberishvili.adapters


import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw8aberishvili.R
import com.example.hw8aberishvili.activities.UserActivity
import com.example.hw8aberishvili.api.User


class UserRecyclerviewAdapter(private var list: List<User>) :
    RecyclerView.Adapter<UserRecyclerviewAdapter.ResourceViewHolder>() {

    companion object {
        const val RESOURCE_ID = "RESOURCE_ID"
    }

    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var name: TextView = itemView.findViewById(R.id.name)
        private var colorBox: ImageView = itemView.findViewById(R.id.colorBox)
        private var year: TextView = itemView.findViewById(R.id.year)
        private var hex: TextView = itemView.findViewById(R.id.hex)
        private var pantoneValue: TextView = itemView.findViewById(R.id.pantone)

        init {
            itemView.setOnClickListener(this)
        }

        private lateinit var user: User

        fun onBind(user: User){
            this.user = user

            name.text = user.name
            colorBox.setBackgroundColor(Color.parseColor(user.color))
            year.text = user.year.toString()
            hex.text = user.color
            pantoneValue.text = user.pantoneValue
        }

        override fun onClick(target: View?) {
            val intent = Intent(itemView.context, UserActivity::class.java)
            intent.putExtra(RESOURCE_ID, user.id)
            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}