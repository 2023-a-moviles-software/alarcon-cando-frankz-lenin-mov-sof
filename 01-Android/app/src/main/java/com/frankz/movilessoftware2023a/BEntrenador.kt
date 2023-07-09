package com.frankz.movilessoftware2023a

class BEntrenador (
    var id: Int,
    var nombre: String?,
    var deescripcion: String?,
) {
    override fun toString(): String {
        return "$id - $nombre - $deescripcion"
    }
}