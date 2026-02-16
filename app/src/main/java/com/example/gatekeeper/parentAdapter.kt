package com.example.gatekeeper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatekeeper.model.User
import com.example.gatekeeper.users

class ParentNameAdapter(private val nameList: List<User>) :
    RecyclerView.Adapter<ParentNameAdapter.ParentNameViewHolder>() {

    class ParentNameViewHolder(itemView: android.view.View) :
        RecyclerView.ViewHolder(itemView) {

        val nameButton: Button = itemView.findViewById(R.id.singleChildButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentNameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_child_name, parent, false)

        return ParentNameViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentNameViewHolder, position: Int) {
        holder.nameButton.text = nameList[position].name
    }

    override fun getItemCount(): Int = nameList.size
}
