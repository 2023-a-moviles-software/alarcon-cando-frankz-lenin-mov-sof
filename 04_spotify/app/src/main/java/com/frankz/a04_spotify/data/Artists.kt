package com.frankz.a04_spotify.data

import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.CardItem

class Artists {
    companion object {
        val items: ArrayList<CardItem> = ArrayList()

        init {
            items.add(CardItem("Feid", R.mipmap.artist_feid_foreground))
            items.add(CardItem("Mike towers", R.mipmap.artist_miketowers_foreground))
            items.add(CardItem("Bad Bunny", R.mipmap.artist_badbunny_foreground))
            items.add(CardItem("Karol G", R.mipmap.artist_karolg_foreground))
            items.add(CardItem("Daddy Yankee", R.mipmap.artist_daddyyankee_foreground))
            items.add(CardItem("Morat", R.mipmap.artist_morat_foreground))
        }
    }
}