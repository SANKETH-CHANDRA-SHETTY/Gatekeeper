package com.example.gatekeeper

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatekeeper.databinding.RequestListBinding
import com.example.gatekeeper.model.User

class RequestList : Fragment(R.layout.request_list) {

    private lateinit var binding: RequestListBinding
    private lateinit var sharedViewModel: SharedUserViewModel
    private lateinit var adapter: RequestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RequestListBinding.bind(view)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedUserViewModel::class.java]

        val curId = sharedViewModel.currentUserId ?: return
        val curUser = users.firstOrNull { it.id == curId } ?: return


        val requestedUsers = users.filter { curUser.requested.contains(it.id) }.toMutableList()


        adapter = RequestAdapter(requestedUsers) { selectedUser, position ->
            curUser.supporter.add(selectedUser.id)
            curUser.requested.remove(selectedUser.id)

            selectedUser.supporter.add(curUser.id)

            requestedUsers.removeAt(position)
            adapter.notifyItemRemoved(position)
        }


        binding.requestRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.requestRecyclerView.adapter = adapter
    }
}