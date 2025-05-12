package com.example.cashflowquestopsc.data.database

import androidx.room.*
import com.example.cashflowquestopsc.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY timestamp DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE date >= :startOfMonth ORDER BY date DESC")
    suspend fun getAllExpensesForCurrentMonth(startOfMonth: String): List<Expense>

}
