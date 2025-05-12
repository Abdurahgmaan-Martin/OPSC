package com.example.cashflowquestopsc.ui

import CategoryViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cashflowquestopsc.data.entities.Category
import com.example.cashflowquestopsc.databinding.ActivityCategoryBinding
import com.example.cashflowquestopsc.ui.adapter.CategoryAdapter

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        val adapter = CategoryAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe categories
        categoryViewModel.allCategories.observe(this, { categories ->
            categories?.let {
                adapter.submitList(it)
            }
        })

        // Add Category
        binding.addCategoryButton.setOnClickListener {
            // Create a new category for testing
            val newCategory = Category(name = "New Category", type = "expense")
            categoryViewModel.addCategory(newCategory)
            Toast.makeText(this, "Category Added", Toast.LENGTH_SHORT).show()
        }
    }
}
