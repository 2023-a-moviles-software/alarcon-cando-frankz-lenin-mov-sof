package dtos

import models.Serie

class StreamingServiceDto(
    val name: String,
    val description: String,
    val price: Double,
    val series: List<Serie>,
) {
}