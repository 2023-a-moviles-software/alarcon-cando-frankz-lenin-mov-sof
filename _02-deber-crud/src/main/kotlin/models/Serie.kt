package models

import java.util.Date

class Serie(
    private val id: String,
    private val title: String,
    private val genre: String,
    private val isFinished: Boolean,
    private val rating: Double,
    private val emissionDate: Date,
    private val streamingService: StreamingService,
) {

    override fun toString(): String {
        return "Serie(id=$id, title='$title', genre='$genre', isFinished=$isFinished, rating=$rating, emissionDate=$emissionDate, streamingService=$streamingService)"
    }
}