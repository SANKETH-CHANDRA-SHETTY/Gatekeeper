package com.example.gatekeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatekeeper.databinding.ActivityParentHomeBinding
import com.example.gatekeeper.model.User
import com.example.gatekeeper.users

class ParentHome : AppCompatActivity() {
    private lateinit var binding: ActivityParentHomeBinding
    private lateinit var sharedViewModel: SharedUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedViewModel = ViewModelProvider(this)[SharedUserViewModel::class.java]

        val id = intent.getStringExtra("userId")
        if (id.isNullOrBlank()) {
            finish()
            return
        }

        sharedViewModel.currentUserId = id
        binding.parentId.text = id

        val recyclerView = binding.parentChildRecycler
        val supporterList = users.firstOrNull { it.id == id }?.supporter ?: mutableListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)

        val supporterUserList = mutableListOf<User>()
        for (sup in supporterList) {
            val supporterUser = users.firstOrNull { it.id == sup } ?: continue
            supporterUserList.add(supporterUser)
        }

        recyclerView.adapter = ParentNameAdapter(supporterUserList)

        replaceFrameWithFragment(RequestConnection())
        supportFragmentManager.beginTransaction()
            .replace(R.id.parentFragmentRequestList, RequestList())
            .commit()

    }

    private fun replaceFrameWithFragment(frag: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.parentFragmentRequestContainer, frag)
        fragmentTransaction.commit()
    }
}
