package com.example.cashflowquestopsc.ui

import ExpenseViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cashflowquestopsc.databinding.ActivityHomeBinding
import com.example.cashflowquestopsc.viewmodel.ExpenseViewModel
import com.example.cashflowquestopsc.adapter.ExpenseAdapter

class HomeActivity : ComponentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        val adapter = ExpenseAdapter()
        binding.recentExpensesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.recentExpensesRecyclerView.adapter = adapter

        // Observe total spent and remaining budget
        viewModel.totalSpent.observe(this) { total ->
            binding.totalSpentTextView.text = "Total Spent: R%.2f".format(total)
        }

        viewModel.remainingBudget.observe(this) { remaining ->
            binding.remainingBudgetTextView.text = "Remaining Budget: R%.2f".format(remaining)
        }

        // Observe recent expenses
        viewModel.recentExpenses.observe(this) { expenses ->
            adapter.submitList(expenses)
        }
    }
}
