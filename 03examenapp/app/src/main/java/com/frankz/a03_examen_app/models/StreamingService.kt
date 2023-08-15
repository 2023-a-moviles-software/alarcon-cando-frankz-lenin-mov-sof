package com.frankz.a03_examen_app.models

import android.os.Build
import androidx.annotation.RequiresApi

class StreamingService(
    private var id: Int,
    private var name: String,
    private var description: String,
    private var price: Double,
    private var series: MutableList<Series>,
) {

    constructor() : this(-1, "", "", 0.0, mutableListOf())

    fun getId(): Int {
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

    fun setId(id: Int) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun addSeries(series: Series) {
        this.series.add(series)
    }

    fun removeSeries(series: Series) {
        this.series = this.series.filter { it.getId() != series.getId() }.toMutableList()
    }

    override fun toString(): String {
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