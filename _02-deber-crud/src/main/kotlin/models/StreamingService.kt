package models

class StreamingService(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val price: Double,
    private val series: List<Serie>,
) {

}