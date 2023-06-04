package models

class StreamingService(
    private val id: String,
    private val name: String,
    private val description: String,
    private val price: Double,
    private val series: List<Serie>,
) {

    public fun getId(): String {
        return id
    }
    public override fun toString(): String {
        val ids: String = series.map { it.getId() }.joinToString(";")
        return "$id,$name,$description,$price,$ids"
    }
}