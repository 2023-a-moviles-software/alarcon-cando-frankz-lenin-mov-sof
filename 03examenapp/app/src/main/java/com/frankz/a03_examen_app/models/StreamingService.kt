package com.frankz.a03_examen_app.models

class StreamingService(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val price: Double,
    private var series: MutableList<Series>,
) {

    constructor() : this(-1, "", "", 0.0, mutableListOf())

    public fun getId(): Int {
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

    fun getSeries(): MutableList<Series> {
        return series
    }

    fun addSeries(series: Series) {
        this.series.add(series)
    }

    fun removeSeries(series: Series) {
        this.series = this.series.filter { it.getId() != series.getId() }.toMutableList()
    }

    public override fun toString(): String {

        return "$name"
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