package services

import dtos.SerieDto
import models.Serie
import models.StreamingService
import java.io.File
import java.util.Date

class SerieService {
    private val file: File = File("src/main/resources/series.txt").also {
        if (!it.exists()) {
            it.createNewFile()
        }
    }

    companion object {
        private var instance: SerieService? = null;

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: SerieService().also { instance = it }
        }
    }

    public fun getAll(): List<Serie> {
        val lines = file.readLines()
        lines.forEach { println(it) }
        return listOf<Serie>()
    }

    public fun getOne(id: Int): Serie {
        println(file.exists())
        return Serie("", "title", "genre", true, 5.0, Date(),
            StreamingService("", "name", "description", 5.0, listOf<Serie>())
        )
    }

    public fun create(serie: SerieDto): Serie {
        val text = "1, title, genre, true, 5.0, 2021-09-01, 1"
        file.appendText(serie.toString() + "\n")
        return Serie("", "title", "genre", true, 5.0, Date(),
            StreamingService("", "name", "description", 5.0, listOf<Serie>())
        )
    }

    public fun update(serie: Serie) {
        println("Serie updated")
    }

    public fun remove(id: Int) {
        println("Serie removed")
    }
}