package com.frankz.a04_spotify.data

import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.CardItem

class PopularStations {
    companion object {
        val items: ArrayList<CardItem> = ArrayList()

        init {
            items.add(CardItem("Feid, Daddy Yankee, Peso Pulma", R.mipmap.estacion_feid_foreground))
            items.add(CardItem("Bad bunny, Jhayco, Chris Jedi", R.mipmap.estacion_badbunny_foreground))
            items.add(CardItem("Karol G, Nio Garcia, Mau y Ricky", R.mipmap.estacion_karolg_foreground))
            items.add(CardItem("", R.mipmap.estacion_morat_foreground))
            items.add(CardItem("", R.mipmap.estacion_tailorswift_foreground))
        }
    }
}