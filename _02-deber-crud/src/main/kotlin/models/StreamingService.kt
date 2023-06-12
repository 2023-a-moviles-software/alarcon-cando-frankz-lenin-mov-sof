package models

class StreamingService(
    private val id: String,
    private val name: String,
    private val description: String,
    private val price: Double,
    private var series: MutableList<Serie>,
) {

    constructor() : this("", "", "", 0.0, mutableListOf())

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

    fun getSeries(): MutableList<Serie> {
        return series
    }

    fun addSeries(series: Serie) {
        this.series.add(series)
    }

    fun removeSeries(series: Serie) {
        this.series = this.series.filter { it.getId() != series.getId() }.toMutableList()
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