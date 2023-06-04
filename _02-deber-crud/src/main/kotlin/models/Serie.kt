package models

import java.util.Date

class Serie(
    private val id: Int,
    private val title: String,
    private val genre: String,
    private val isFinished: Boolean,
    private val rating: Double,
    private val emissionDate: Date,
    private val streamingService: StreamingService,
) {

}