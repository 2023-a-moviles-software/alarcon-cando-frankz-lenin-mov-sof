package services

import dtos.SerieDto
import models.Serie
import java.io.File
import java.time.LocalDate

class SeriesService {
    private val file: File = File("src/main/resources/series.txt").also {
        if (!it.exists()) {
            it.createNewFile()
        }
    }

    companion object {
        private var instance: SeriesService? = null;

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: SeriesService().also { instance = it }
        }
    }

    private fun randomString(): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..10)
            .map { chars.random() }
            .joinToString("")
    }


    public fun getAll(): List<Serie> {
        val lines = file.readLines()
        val series = lines.map { it ->
            val seriesSplit = it.split(",")
            val streamingService = StreamingServiceService.getInstance().getOne(seriesSplit[6]) ?: return listOf<Serie>()
            return@map Serie(
                seriesSplit[0],
                seriesSplit[1],
                seriesSplit[2],
                seriesSplit[3].toBoolean(),
                seriesSplit[4].toInt(),
                LocalDate.parse(seriesSplit[5]),
                streamingService
            )
        }

        return series
    }

    fun getOne(id: String): Serie? {
        val lines = file.readLines()
        val seriesString = lines.find { it.split(",")[0] == id } ?: return null

        val seriesSplit = seriesString.split(",")
        val streamingService = StreamingServiceService.getInstance().getOne(seriesSplit[6]) ?: return null

        return Serie(
            seriesSplit[0],
            seriesSplit[1],
            seriesSplit[2],
            seriesSplit[3].toBoolean(),
            seriesSplit[4].toInt(),
            LocalDate.parse(seriesSplit[5]),
            streamingService
        )
    }

    fun create(series: SerieDto): Serie {
        val newSeries = Serie(
            id = randomString(),
            title = series.title,
            genre = series.genre,
            isFinished = series.isFinished,
            seasons = series.seasons,
            emissionDate = series.emissionDate,
            streamingService = series.streamingService
        )
        file.appendText(newSeries.toString() + "\n")
        return newSeries
    }

    fun update(serie: Serie) {
        println("Series updated")
    }

    fun remove(id: Int) {
        println("Series removed")
    }
}