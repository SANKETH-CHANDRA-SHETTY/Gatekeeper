package com.example.gatekeeper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gatekeeper.model.User

class RequestAdapter(
    private var userList: MutableList<User>,
    private val onAcceptClick: (User, Int) -> Unit
) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.requestAcceptionName)
        val acceptButton: Button = itemView.findViewById(R.id.requestAceeptionButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_request_acception, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val user = userList[position]
        holder.nameText.text = user.name
        holder.acceptButton.setOnClickListener {
            onAcceptClick(user, position)
        }
    }

    override fun getItemCount(): Int = userList.size

    // Helper to refresh list after removal
    fun removeItem(position: Int) {
        // We don't remove from userList here because we'll refresh the whole list
        // from the Fragment for better data integrity
        notifyItemRemoved(position)
    }
}