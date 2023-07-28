package com.frankz.a04_spotify.data

import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.CategoryCardItem

class CategoryCardItems {
    companion object {
        val items: ArrayList<CategoryCardItem> = ArrayList()

        init {
            items.add(CategoryCardItem("Podcast", "#e13300", R.mipmap.category_podcasts_foreground))
            items.add(CategoryCardItem("Eventos en vivo", "#7358ff", R.mipmap.category_lives_foreground))
            items.add(CategoryCardItem("Creados para ti", "#1e3264", R.mipmap.category_relax_foreground))
            items.add(CategoryCardItem("Latina", "#e1118c", R.mipmap.category_training_foreground))
            items.add(CategoryCardItem("Hip Hop", "#bc5900", R.mipmap.category_hiphop_foreground))
            items.add(CategoryCardItem("Pop", "#148a08", R.mipmap.category_pop_foreground))
            items.add(CategoryCardItem("Cumbia", "#509bf5", R.mipmap.category_cumbia_indie))
            items.add(CategoryCardItem("Alternativa", "#e13300", R.mipmap.category_altenatives_foreground))
            items.add(CategoryCardItem("Latina", "#e1118c", R.mipmap.category_training_foreground))
            items.add(CategoryCardItem("Hip Hop", "#bc5900", R.mipmap.category_hiphop_foreground))
            items.add(CategoryCardItem("Pop", "#148a08", R.mipmap.category_pop_foreground))
            items.add(CategoryCardItem("Cumbia", "#509bf5", R.mipmap.category_cumbia_indie))
            items.add(CategoryCardItem("Alternativa", "#e13300", R.mipmap.category_altenatives_foreground))
            items.add(CategoryCardItem("Podcast", "#e13300", R.mipmap.category_podcasts_foreground))
            items.add(CategoryCardItem("Eventos en vivo", "#7358ff", R.mipmap.category_lives_foreground))
            items.add(CategoryCardItem("Creados para ti", "#1e3264", R.mipmap.category_relax_foreground))
        }
    }
}