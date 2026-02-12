package com.example.gatekeeper

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(private val nameList: List<String>) :
    RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    class NameViewHolder(val view: android.view.View) :
        RecyclerView.ViewHolder(view) {

        val nameTextView: TextView =
            view.findViewById(R.id.singleParentName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_parent_name, parent, false)

        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.nameTextView.text = nameList[position]
    }

    override fun getItemCount(): Int = nameList.size
}
