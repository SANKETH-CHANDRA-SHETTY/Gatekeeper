package com.example.gatekeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatekeeper.databinding.ActivityParentHomeBinding
import com.example.gatekeeper.model.User
import com.example.gatekeeper.users

class ParentHome : AppCompatActivity() {
    private lateinit var binding: ActivityParentHomeBinding
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
        val id=intent.getStringExtra("userId")
        binding.parentId.text=id

        val recyclerView = binding.parentChildRecycler
        val supporterList = users.first { it.id == id }.supporter

        recyclerView.layoutManager = LinearLayoutManager(this)

        val supporterUserList= mutableListOf<User>()
        for(sup in supporterList){
            supporterUserList.add(users.first { it.id == sup })
        }

        recyclerView.adapter = ParentNameAdapter(supporterUserList)

        replaceFrameWithFragment(RequestConnection())

    }

    private fun replaceFrameWithFragment(frag:Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.parentFragmentRequestContainer,frag)
        fragmentTransaction.commit()
    }
}