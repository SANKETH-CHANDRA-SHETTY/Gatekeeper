package com.example.gatekeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatekeeper.databinding.ActivityParentHomeBinding

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
        var supporterNames= ArrayList<String>()
        for(sup in supporterList){
            supporterNames.add(users.first { it.id == sup }.name)
        }
        recyclerView.adapter = ParentNameAdapter(supporterNames)
    }
}