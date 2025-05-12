package com.example.cashflowquestopsc.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cashflowquestopsc.data.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display dummy profile info for now
        binding.userEmail.text = "Email: user@example.com" // Replace with real data
        binding.userName.text = "Name: John Doe"  // Replace with real data

        // Theme switch (you can add actual logic for dark/light mode if needed)
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Logic for changing theme (e.g., switch between dark and light mode)
            Toast.makeText(this, "Theme switching coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Reset data button
        binding.resetButton.setOnClickListener {
            showResetConfirmation()
        }
    }

    private fun showResetConfirmation() {
        // Show a confirmation dialog before resetting data
        AlertDialog.Builder(this)
            .setTitle("Confirm Reset")
            .setMessage("Are you sure you want to delete all app data?")
            .setPositiveButton("Yes") { _, _ ->
                resetDatabase()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun resetDatabase() {
        val db = AppDatabase.getInstance(this)

        // Coroutine to delete data in the database
        CoroutineScope(Dispatchers.IO).launch {
            db.expenseDao().deleteAll()  // Assuming expenseDao has a deleteAll method
            db.transactionDao().deleteAll()  // Same for transactionDao
            db.categoryDao().getAllCategories().forEach {
                db.categoryDao().deleteCategory(it)
            }

            // After deletion, show a message to the user
            runOnUiThread {
                Toast.makeText(this@SettingsActivity, "All data reset", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
