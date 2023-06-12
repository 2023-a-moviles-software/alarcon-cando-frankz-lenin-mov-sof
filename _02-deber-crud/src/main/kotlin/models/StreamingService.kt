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

    fun getName(): String {
        return name
    }

    fun getDescription(): String {
        return description
    }

    fun getPrice(): Double {
        return price
    }

    fun getSeries(): List<Serie> {
        return series
    }

    public override fun toString(): String {
        val ids: String = series.map { it.getId() }.joinToString(";")
        return "$id,$name,$description,$price,$ids"
    }

    fun getListOfStringFromData(): List<String> {
        return listOf(
            "Nombre: $name",
            "Descripción: $description",
            "Precio: $price",
            "Series: ${series.map { it.getTitle() }}",
        )
    }
}