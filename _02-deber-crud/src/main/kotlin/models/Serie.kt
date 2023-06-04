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


    override fun toString(): String {
        return "$id,$title,$genre,$isFinished,$seasons,$emissionDate,${streamingService.getId()}"
    }
}