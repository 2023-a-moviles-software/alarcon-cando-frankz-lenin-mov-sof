package com.frankz.a03_examen_app.dtos

class StreamingServiceDto(
    private var name: String,
    private var description: String,
    private var price: Double,
) {
    constructor() : this("", "", 0.0)

    fun getName(): String {
        return name
    }

    fun getDescription(): String {
        return description
    }

    fun getPrice(): Double {
        return price
    }
}