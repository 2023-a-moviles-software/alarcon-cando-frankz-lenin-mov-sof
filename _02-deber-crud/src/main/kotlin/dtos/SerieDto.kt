package dtos

import models.StreamingService

class SerieDto (
    private val title: String,
    private val genre: String,
    private val isFinished: Boolean,
    private val rating: Double,
    private val emissionDate: String,
    private val streamingService: StreamingService,
){
}