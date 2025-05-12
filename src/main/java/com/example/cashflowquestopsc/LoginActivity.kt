package com.example.cashflowquestopsc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cashflowquestopsc.data.database.AppDatabase
import com.example.cashflowquestopsc.ui.auth.RegistrationActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerRedirect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        registerRedirect = findViewById(R.id.registerRedirect)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val userDao = AppDatabase.getDatabase(applicationContext).userDao()
                    val user = userDao.getUserByUsername(username)

                    withContext(Dispatchers.Main) {
                        if (user != null && user.password == password) { // Replace with hashing comparison later
                            // Store user ID in SharedPreferences
                            val prefs = getSharedPreferences("session", MODE_PRIVATE)
                            prefs.edit().putInt("userId", user.userId.toInt()).apply()

                            Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        registerRedirect.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}
