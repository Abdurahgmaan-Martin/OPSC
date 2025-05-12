package com.example.cashflowquestopsc.data.database

import UserDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cashflowquestopsc.data.dao.TransactionDao
import com.example.cashflowquestopsc.data.dao.CategoryDao
import com.example.cashflowquestopsc.data.entities.Transaction
import com.example.cashflowquestopsc.data.entities.Category

@Database(entities = [Transaction::class, Category::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): UserDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cash_flow_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}