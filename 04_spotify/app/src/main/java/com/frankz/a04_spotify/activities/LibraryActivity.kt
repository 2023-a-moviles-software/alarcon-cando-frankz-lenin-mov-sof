package com.frankz.a04_spotify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.adapters.AlbumItemAdapter
import com.frankz.a04_spotify.adapters.CategoryItemAdapter
import com.frankz.a04_spotify.adapters.RecentAdapter
import com.frankz.a04_spotify.data.Recients
import com.frankz.a04_spotify.models.AlbumItem
import com.frankz.a04_spotify.models.CategoryCardItem

class LibraryActivity : AppCompatActivity() {
    private val recients = Recients.items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        // Load recients
        val recentsRecyclerView = findViewById<RecyclerView>(R.id.rv_all)
        loadRecients(recentsRecyclerView, recients)

        // Set buttons listeners
        setButtonsListeners()
    }

    private fun loadRecients(recyclerView: RecyclerView, recientItems: ArrayList<AlbumItem>) {
        val adapter = RecentAdapter(
            this,
            recientItems,
            recyclerView
        )

        recyclerView.adapter = adapter

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.notifyDataSetChanged()
    }

    private fun setButtonsListeners() {
        val homeButton = findViewById<ImageButton>(R.id.btn_home)
        homeButton.setOnClickListener {
            finish()
        }

        val searchButton = findViewById<ImageButton>(R.id.btn_search)
        searchButton.setOnClickListener {
            finish()
            goToActivity(SearchActivity::class.java)
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