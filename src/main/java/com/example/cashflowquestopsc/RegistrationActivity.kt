package com.example.cashflowquestopsc.ui.auth


import kotlinx.coroutines.Dispatchers
import User
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cashflowquestopsc.R
import com.example.cashflowquestopsc.data.database.AppDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class RegistrationActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    private val userDao by lazy {
        AppDatabase.getDatabase(applicationContext).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        registerButton = findViewById(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showToast("All fields are required.")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                showToast("Passwords do not match.")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existingUser = withContext(Dispatchers.IO) {
                    userDao.getUserByUsername(username)
                }

                if (existingUser != null) {
                    showToast("Username already taken.")
                } else {
                    val hashedPassword = hashPassword(password)
                    val newUser = User(username = username, password = hashedPassword)

                    withContext(Dispatchers.IO) {
                        userDao.insertUser(newUser)
                    }

                    showToast("Registration successful!")
                    finish() // Close activity or navigate to login
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@RegistrationActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
