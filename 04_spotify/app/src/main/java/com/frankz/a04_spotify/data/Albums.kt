package com.frankz.a04_spotify.data

import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.AlbumItem

class Albums {
    companion object {
        val items: ArrayList<AlbumItem> = ArrayList()

        init {
            items.add(AlbumItem("Un Verano Sin Ti", "Álbum · Bad Bunny", R.mipmap.album_un_verano_sin_ti_foreground))
                items.add(AlbumItem("FELIZ CUMPLEAÑOS FERXXO TE PIRATEAMOS EL ÁLBUM", "Álbum · Feid", R.mipmap.album_feliz_cumpleanos_foreground))
            items.add(AlbumItem("DATA", "Álbum · Tainy", R.mipmap.album_data_foreground))
            items.add(AlbumItem("MAÑANA SERÁ BONITO", "Álbum · KAROL G", R.mipmap.album_manana_sera_bonita_foreground))
            items.add(AlbumItem("Balas Perdidas", "Álbum · Morat", R.mipmap.album_balas_perdidas_foreground))
        }
    }
}