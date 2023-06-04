package dtos

class StreamingServiceDto(
    private val name: String,
    private val description: String,
    private val price: Double,
    private val series: List<SerieDto>,
) {
}