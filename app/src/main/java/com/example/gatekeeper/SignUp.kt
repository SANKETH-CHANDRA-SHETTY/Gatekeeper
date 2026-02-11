package com.example.gatekeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gatekeeper.databinding.ActivitySignUpBinding
import android.content.Intent
import com.example.gatekeeper.users
import com.example.gatekeeper.model.User
import android.widget.Toast



class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.signUpLogin.setOnClickListener{
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
        }

        binding.signUpSubmit.setOnClickListener {

            val enteredName = binding.signUpName.text.toString().trim()
            val enteredEmailAddress = binding.signUpEmailAddress.text.toString().trim()
            val enteredPassword = binding.signUpPassword.text.toString().trim()
            val enteredPhoneNo = binding.signUpPhoneNo.text.toString().trim()

            val selectedRoleId = binding.signUpRole.checkedRadioButtonId

            // ✅ Validation
            if (enteredName.isEmpty() ||
                enteredEmailAddress.isEmpty() ||
                enteredPassword.isEmpty() ||
                enteredPhoneNo.isEmpty() ||
                selectedRoleId == -1
            ) {
                Toast.makeText(this, getString(R.string.fillAllFields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Correct way to get selected radio button text
            val selectedRadioButton = findViewById<android.widget.RadioButton>(selectedRoleId)
            val selectedRole = selectedRadioButton.text.toString().lowercase()

            // ✅ Check if email already exists
            if (users.any { it.emailAddress == enteredEmailAddress }) {
                Toast.makeText(this, getString(R.string.userExists), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Generate unique ID properly
            var tryId: String
            do {
                tryId = ""
                repeat(3) { tryId += ('A'..'Z').random() }
                repeat(3) { tryId += (0..9).random() }
            } while (users.any { it.id == tryId })

            val newUser = User(
                id = tryId,
                name = enteredName,
                phoneNo = enteredPhoneNo,
                emailAddress = enteredEmailAddress,
                password = enteredPassword,
                role = selectedRole,
                supporter = mutableListOf()
            )

            users.add(newUser)

            Toast.makeText(this, getString(R.string.userAdded), Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, Login::class.java))
        }

    }
}