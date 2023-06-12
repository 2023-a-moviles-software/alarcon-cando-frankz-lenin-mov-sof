package models

import java.time.LocalDate
import java.util.Date

class Serie(
    private val id: String,
    private val title: String,
    private val genre: String,
    private val isFinished: Boolean,
    private val seasons: Int,
    private val emissionDate: LocalDate,
    private val streamingService: StreamingService,
) {

    public fun getId(): String {
        return id
    }

    public fun getTitle(): String {
        return title
    }

    fun getGenre(): String {
        return genre
    }

    fun getIsFinished(): Boolean {
        return isFinished
    }

    fun getSeasons(): Int {
        return seasons
    }

    fun getEmissionDate(): LocalDate {
        return emissionDate
    }

    fun getStreamingService(): StreamingService {
        return streamingService
    }


    override fun toString(): String {
        return "$id,$title,$genre,$isFinished,$seasons,$emissionDate,${streamingService.getId()}"
    }

    fun getListOfStringFromData(): List<String> {
        return listOf(
            "Título: $title",
            "Género: $genre",
            "Finalizada: $isFinished",
            "Temporadas: $seasons",
            "Fecha de emisión: $emissionDate",
            "Plataforma: ${streamingService.getName()}",
        )
    }
}