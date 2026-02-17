package com.example.gatekeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gatekeeper.databinding.ActivityLoginBinding
import android.content.Intent
import com.example.gatekeeper.users
import com.example.gatekeeper.model.User
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        binding.loginSubmit.setOnClickListener {
            val enteredEmailAddress = binding.loginEmailAddress.text.toString()
            val enteredPassword = binding.loginPassword.text.toString()
            var loggedUser: User? = null
            for (user in users) {
                if (user.emailAddress == enteredEmailAddress) {
                    loggedUser = user
                    if (user.password == enteredPassword) {
                        if (loggedUser.role == "child") {
                            val intent = Intent(this, ChildHome::class.java)
                            intent.putExtra("userId", loggedUser.id)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else if (loggedUser.role == "parent") {
                            val intent = Intent(this, ParentHome::class.java)
                            intent.putExtra("userId", loggedUser.id)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(this, getString(R.string.incorrectPassword), Toast.LENGTH_LONG).show()
                    }
                    break
                }
            }
            if (loggedUser == null) {
                Toast.makeText(this, getString(R.string.incorrectemailid), Toast.LENGTH_LONG).show()
            }
        }
    }
}
