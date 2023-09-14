package com.frankz.a03_examen_app.dtos

class SeriesDto(
    private var title: String,
    private var genre: String,
    private var isFinished: Boolean,
    private var seasons: Int,
    private var emissionDate: String,
    private var streamingServiceId: String,
) {
    constructor() : this("", "", false, 0, "", "")

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
}