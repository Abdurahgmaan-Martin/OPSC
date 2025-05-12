package com.example.cashflowquestopsc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cashflowquestopsc.databinding.ItemExpenseBinding
import com.example.cashflowquestopsc.data.entities.Expense
import com.example.cashflowquestopsc.data.model.Expense

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private var expenses: List<Expense> = emptyList()

    fun submitList(list: List<Expense>) {
        expenses = list
        notifyDataSetChanged()
    }

    inner class ExpenseViewHolder(private val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(expense: Expense) {
            binding.expenseNameText.text = expense.name
            binding.expenseAmountText.text = "R%.2f".format(expense.amount)
            binding.expenseDateText.text = expense.date // Format this nicely if needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int = expenses.size
}
