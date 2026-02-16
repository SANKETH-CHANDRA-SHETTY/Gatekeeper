package com.example.gatekeeper

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gatekeeper.databinding.RequestConnectionBinding
import com.example.gatekeeper.users

class RequestConnection() : Fragment(R.layout.request_connection) {

    private lateinit var binding: RequestConnectionBinding
    private lateinit var sharedViewModel: SharedUserViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = RequestConnectionBinding.bind(view)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedUserViewModel::class.java]
        val curId = sharedViewModel.currentUserId ?: return

        val curUser=users.first{ it.id == curId }


        binding.fragmentRequestButton.setOnClickListener {
            val reqId=binding.connectionFragmentId.text.toString()

            val reqUser=users.firstOrNull { it.id == reqId }
            if (reqUser != null) {
                if(reqUser.id in curUser.requested){
                    reqUser.supporter.add(curUser.id)
                    curUser.supporter.add(reqUser.id)
                    curUser.requested.remove(reqUser.id)
                }
                else if(curId in reqUser.requested){
                    binding.fragmentRequestResult.text = getString(R.string.alreadyRequested)
                }
                else if(reqId in curUser.supporter) {
                    binding.fragmentRequestResult.text = "${reqUser.name} is already connected"
                }
                else{
                    if(reqUser.role==curUser.role){
                        binding.fragmentRequestResult.text = "${reqUser.name} is of the same role as you. So cannot be connected"

                    }
                    else {
                        binding.fragmentRequestResult.text = "${reqUser.name} has been requested to connect"
                        reqUser.requested.add(curUser.id)
                    }
                }
            }  else {
                binding.fragmentRequestResult.text = getString(R.string.notuser)


            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
            //binding = null
    }
}
