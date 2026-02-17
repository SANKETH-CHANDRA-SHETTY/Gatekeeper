package com.example.gatekeeper
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gatekeeper.databinding.ActivityChildHomeBinding
import com.example.gatekeeper.users
import com.example.gatekeeper.NameAdapter
import androidx.fragment.app.Fragment
import com.example.gatekeeper.RequestConnection

class ChildHome : AppCompatActivity() {
    private lateinit var binding: ActivityChildHomeBinding
    private lateinit var sharedViewModel: SharedUserViewModel

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

        sharedViewModel = ViewModelProvider(this)[SharedUserViewModel::class.java]

        val id = intent.getStringExtra("userId")
        if (id.isNullOrBlank()) {
            finish()
            return
        }

        sharedViewModel.currentUserId = id
        binding.childId.text = id

        val recyclerView = binding.childParentRecycler
        val supporterList = users.firstOrNull { it.id == id }?.supporter ?: mutableListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)
        val supporterNames = ArrayList<String>()
        for (sup in supporterList) {
            val supporterName = users.firstOrNull { it.id == sup }?.name ?: continue
            supporterNames.add(supporterName)
        }
        recyclerView.adapter = NameAdapter(supporterNames)

        replaceFrameWithFragment(RequestConnection())
        supportFragmentManager.beginTransaction()
            .replace(R.id.childFragmentRequestList, RequestList())
            .commit()


    }

    private fun replaceFrameWithFragment(frag: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.childFragmentRequestContainer, frag)
        fragmentTransaction.commit()
    }
}
