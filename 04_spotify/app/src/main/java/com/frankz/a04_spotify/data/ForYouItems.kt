package com.frankz.a04_spotify.data

import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.CardItem

class ForYouItems {
    companion object {
        val items: ArrayList<CardItem> = ArrayList()
        init {
            items.add(CardItem("Mago de Oz, Genitallica, Warcry y más", R.mipmap.mix_diario_1_foreground))
            items.add(CardItem("System Of A Down, Linkin Park, Red Hot CHilli Peppers y más", R.mipmap.mix_diario_2_foreground))
        }
    }
}