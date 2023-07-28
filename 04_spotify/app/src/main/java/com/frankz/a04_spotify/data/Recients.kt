package com.frankz.a04_spotify.data

import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.AlbumItem

class Recients {
    companion object {
        val items: ArrayList<AlbumItem> = ArrayList()

        init {
            items.add(AlbumItem("Feid", "Artista", R.mipmap.artist_feid_foreground))
            items.add(AlbumItem("Mago de Oz", "Artista", R.mipmap.mix_diario_1_foreground))
            items.add(AlbumItem("Un Verano Sin ti", "Álbum · Bad Bunny", R.mipmap.album_un_verano_sin_ti_foreground))
            items.add(AlbumItem("Bad Bunny", "Artista", R.mipmap.artist_badbunny_foreground))
            items.add(AlbumItem("Balas Perdidas", "Álbum · Morat", R.mipmap.album_balas_perdidas_foreground))
            items.add(AlbumItem("FELIZ CUMPLEAÑOS FERXXO", "Álbum · Feid", R.mipmap.album_feliz_cumpleanos_foreground))
            items.add(AlbumItem("Daddy Yankee", "Artista", R.mipmap.artist_daddyyankee_foreground))
            items.add(AlbumItem("DATA", "Álbum · Tainy", R.mipmap.album_data_foreground))
            items.add(AlbumItem("Feid", "Artista", R.mipmap.artist_feid_foreground))
            items.add(AlbumItem("Mago de Oz", "Artista", R.mipmap.mix_diario_1_foreground))
            items.add(AlbumItem("Un Verano Sin ti", "Álbum · Bad Bunny", R.mipmap.album_un_verano_sin_ti_foreground))
        }
    }
}