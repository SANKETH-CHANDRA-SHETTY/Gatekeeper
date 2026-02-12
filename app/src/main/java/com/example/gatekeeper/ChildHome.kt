package com.example.gatekeeper
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatekeeper.databinding.ActivityChildHomeBinding
import com.example.gatekeeper.users
import com.example.gatekeeper.NameAdapter


class ChildHome : AppCompatActivity() {
    private lateinit var binding: ActivityChildHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id=intent.getStringExtra("userId")
        binding.childId.text=id

        val recyclerView = binding.childParentRecycler
        val supporterList = users.first { it.id == id }.supporter

        recyclerView.layoutManager = LinearLayoutManager(this)
        var supporterNames= ArrayList<String>()
        for(sup in supporterList){
            supporterNames.add(users.first { it.id == sup }.name)
        }
        recyclerView.adapter = NameAdapter(supporterNames)
    }
}