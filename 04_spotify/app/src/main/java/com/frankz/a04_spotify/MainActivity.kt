package com.frankz.a04_spotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frankz.a04_spotify.activities.LibraryActivity
import com.frankz.a04_spotify.activities.SearchActivity
import com.frankz.a04_spotify.activities.SpotifyActivity
import com.frankz.a04_spotify.adapters.AlbumItemAdapter
import com.frankz.a04_spotify.adapters.ArtistItemAdapter
import com.frankz.a04_spotify.adapters.ForYouItemAdapter
import com.frankz.a04_spotify.data.Albums
import com.frankz.a04_spotify.data.Artists
import com.frankz.a04_spotify.data.ForYouItems
import com.frankz.a04_spotify.data.PopularStations
import com.frankz.a04_spotify.models.AlbumItem
import com.frankz.a04_spotify.models.CardItem

class MainActivity : AppCompatActivity() {

    private val forYouItems = ForYouItems.items
    private val popularStations = PopularStations.items
    private val artists = Artists.items
    private val albums = Albums.items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load "For you" section
        val forYouRecyclerView = findViewById<RecyclerView>(R.id.rv_categories)
        loadCardItems(forYouRecyclerView, forYouItems)

        // load "Popular stations" section
        val popularStationsRecyclerView = findViewById<RecyclerView>(R.id.rv_popular_stations)
        loadCardItems(popularStationsRecyclerView, popularStations)

        // load "Artists" section
        val artistsRecyclerView = findViewById<RecyclerView>(R.id.rv_artists)
        loadCircularCardItems(artistsRecyclerView, artists)

        // load "Albums" section
        val albumsRecyclerView = findViewById<RecyclerView>(R.id.rv_albums)
        loadAlbumItems(albumsRecyclerView, albums)

        // Set buttons listeners
        setButtonsListeners()
    }

    private fun loadCardItems(recyclerView: RecyclerView, cardItemsList: ArrayList<CardItem>) {
        val adapter = ForYouItemAdapter(
            this,
            cardItemsList,
            recyclerView
        )

        recyclerView.adapter = adapter

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter.notifyDataSetChanged()
    }

    private fun loadCircularCardItems(recyclerView: RecyclerView, cardItemsList: ArrayList<CardItem>) {
        val adapter = ArtistItemAdapter(
            this,
            cardItemsList,
            recyclerView
        )

        recyclerView.adapter = adapter

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter.notifyDataSetChanged()
    }

    private fun loadAlbumItems(recyclerView: RecyclerView, albumItemsList: ArrayList<AlbumItem>) {
        val adapter = AlbumItemAdapter(
            this,
            albumItemsList,
            recyclerView
        )

        recyclerView.adapter = adapter

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter.notifyDataSetChanged()
    }

    private fun setButtonsListeners() {
        val searchButton = findViewById<ImageButton>(R.id.btn_search)
        searchButton.setOnClickListener {
            goToActivity(SearchActivity::class.java)
        }

        val libraryButton = findViewById<ImageButton>(R.id.btn_library)
        libraryButton.setOnClickListener {
            goToActivity(LibraryActivity::class.java)
        }

        val spotifyButton = findViewById<ImageButton>(R.id.btn_spotify)
        spotifyButton.setOnClickListener {
            goToActivity(SpotifyActivity::class.java)
        }
    }

    private fun goToActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

}