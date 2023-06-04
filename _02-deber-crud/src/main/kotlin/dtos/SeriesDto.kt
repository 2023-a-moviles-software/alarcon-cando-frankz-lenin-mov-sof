package dtos

import models.StreamingService
import java.time.LocalDate

class SeriesDto (
    val title: String,
    val genre: String,
    val isFinished: Boolean,
    val seasons: Int,
    val emissionDate: LocalDate,
    val streamingService: StreamingService,
){
}