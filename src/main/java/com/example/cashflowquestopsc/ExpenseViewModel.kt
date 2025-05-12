package com.example.cashflowquestopsc.viewmodel

import androidx.lifecycle.*




import java.util.*

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cashflowquestopsc.data.database.AppDatabase
import com.example.cashflowquestopsc.data.model.Expense
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).expenseDao()

    fun addExpense(amount: Double, category: String, note: String?, photoUri: Uri?) {
        viewModelScope.launch {
            val expense = Expense(
                amount = amount,
                category = category,
                note = note,
                photoUri = photoUri?.toString()
            )
            dao.insert(expense)
        }
    }

        private val _expenses = MutableLiveData<List<Expense>>()
        val expenses: LiveData<List<Expense>> = _expenses

        val recentExpenses: LiveData<List<Expense>> = Transformations.map(expenses) {
            it.sortedByDescending { expense -> expense.date }.take(5)
        }

        val totalSpent: LiveData<Double> = Transformations.map(expenses) {
            it.sumOf { expense -> expense.amount }
        }

        private val _monthlyBudget = MutableLiveData<Double>(2000.0) // Default or load from DB
        val monthlyBudget: LiveData<Double> = _monthlyBudget

        val remainingBudget: LiveData<Double> = MediatorLiveData<Double>().apply {
            addSource(totalSpent) { spent ->
                value = _monthlyBudget.value?.minus(spent) ?: 0.0
            }
            addSource(_monthlyBudget) { budget ->
                value = budget - (totalSpent.value ?: 0.0)
            }
        }
    fun loadExpenses() {
        viewModelScope.launch {
            val db
            _expenses.value = db.expenseDao().getAllExpensesForCurrentMonth(getStartOfMonth())
        }
    }

    private fun getStartOfMonth(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(calendar.time)
    }

    fun updateMonthlyBudget(amount: Double) {
        _monthlyBudget.value = amount
    }
    }

