package com.frankz.a04_spotify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.frankz.a04_spotify.R

class SpotifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spotify)

        // Set buttons listeners
        setButtonsListeners()
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

        val libraryActivity = findViewById<ImageButton>(R.id.btn_library)
        libraryActivity.setOnClickListener {
            finish()
            goToActivity(LibraryActivity::class.java)
        }
    }

    private fun goToActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}