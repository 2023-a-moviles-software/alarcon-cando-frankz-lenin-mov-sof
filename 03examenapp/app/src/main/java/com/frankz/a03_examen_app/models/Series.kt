package com.frankz.a03_examen_app.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Date

class Series(
    private val id: Int,
    private val title: String,
    private val genre: String,
    private val isFinished: Boolean,
    private val seasons: Int,
    private val emissionDate: LocalDate,
    private val streamingService: StreamingService,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(-1, "", "", false, 0, LocalDate.now(), StreamingService())

    public fun getId(): Int {
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