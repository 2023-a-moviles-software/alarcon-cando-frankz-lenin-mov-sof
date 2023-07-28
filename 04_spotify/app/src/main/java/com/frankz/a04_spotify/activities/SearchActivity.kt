package com.frankz.a04_spotify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.adapters.CategoryItemAdapter
import com.frankz.a04_spotify.adapters.ForYouItemAdapter
import com.frankz.a04_spotify.data.CategoryCardItems
import com.frankz.a04_spotify.models.CardItem
import com.frankz.a04_spotify.models.CategoryCardItem

class SearchActivity : AppCompatActivity() {
    private val categories = CategoryCardItems.items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // load "Categories" section
        val categoriesRecyclerView = findViewById<RecyclerView>(R.id.rv_categories)
        loadcategoryItems(categoriesRecyclerView, categories)

        // Set buttons listeners
        setButtonsListeners()
    }

    private fun loadcategoryItems(recyclerView: RecyclerView, categoryCardItems: ArrayList<CategoryCardItem>) {
        val adapter = CategoryItemAdapter(
            this,
            categoryCardItems,
            recyclerView
        )

        recyclerView.adapter = adapter

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.notifyDataSetChanged()
    }


    private fun setButtonsListeners() {
        val homeButton = findViewById<ImageButton>(R.id.btn_home)
        homeButton.setOnClickListener {
            finish()
        }

        val libraryButton = findViewById<ImageButton>(R.id.btn_library)
        libraryButton.setOnClickListener {
            finish()
            goToActivity(LibraryActivity::class.java)
        }

        val spotifyButton = findViewById<ImageButton>(R.id.btn_spotify)
        spotifyButton.setOnClickListener {
            finish()
            goToActivity(SpotifyActivity::class.java)
        }
    }

    private fun goToActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}