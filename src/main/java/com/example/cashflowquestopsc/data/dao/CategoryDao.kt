package com.example.cashflowquestopsc.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cashflowquestopsc.data.entities.Category

@Dao
interface CategoryDao {
    @Insert
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<Category>>
}
