package com.frankz.a03_examen_app.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
class Series(
    private var id: String,
    private var title: String,
    private var genre: String,
    private var isFinished: Boolean,
    private var seasons: Int,
    private var emissionDate: LocalDate,
    private var streamingService: StreamingService,
) {

    constructor() : this("", "", "", false, 0, LocalDate.now(), StreamingService())

    fun getId(): String {
        return id
    }

    fun getTitle(): String {
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

    fun setTitle(title: String) {
        this.title = title
    }

    fun setGenre(genre: String) {
        this.genre = genre
    }

    fun setIsFinished(isFinished: Boolean) {
        this.isFinished = isFinished
    }

    fun setSeasons(seasons: Int) {
        this.seasons = seasons
    }

    fun setEmissionDate(emissionDate: LocalDate) {
        this.emissionDate = emissionDate
    }

    fun setStreamingService(streamingService: StreamingService) {
        this.streamingService = streamingService
    }


    fun getStreamingService(): StreamingService {
        return streamingService
    }


    override fun toString(): String {
        return "$title"
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