package com.frankz.a03_examen_app.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Date

class Series(
    private var id: String,
    private var title: String,
    private var genre: String,
    private var isFinished: Boolean,
    private var seasons: Int,
    private var emissionDate: String,
    private var streamingServiceId: String,
) {

    constructor() : this("", "", "", false, 0, "", "")

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
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

    fun getEmissionDate(): String {
        return emissionDate
    }

    fun getStreamingServiceId(): String {
        return streamingServiceId
    }

    fun setEmissionDate(emissionDate: String) {
        this.emissionDate = emissionDate
    }

    fun setStreamingServiceId(streamingServiceId: String) {
        this.streamingServiceId = streamingServiceId
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

    override fun toString(): String {
        return "$title"
    }
}