package views

import dtos.SeriesDto
import models.Serie
import services.SeriesService
import services.StreamingServiceService
import java.time.LocalDate

class SeriesView {

    val tables = ConsoleTable()

    fun selectSeriesMenu(parent: MainView) {
        var goBack = false
        do {
            println("Seleccione una opción:")
            println("1. Listar series")
            println("2. Crear serie")
            println("3. Actualizar serie")
            println("4. Eliminar serie")
            println("5. Volver atrás")

            val option = readln().toInt()
            when (option) {
                1 -> listAllSeries()
                2 -> createSerie()
                3 -> SeriesService.getInstance().getAll().forEach { println(it) }
                4 -> SeriesService.getInstance().getAll().forEach { println(it) }
                5 -> {
                    goBack = true
                    parent.init()
                }
                else -> println("Opción no válida")
            }
        } while (!goBack)
    }

    fun listAllSeries() {
        val series: List<Serie> = SeriesService.getInstance().getAll()

        if (series.isEmpty()) {
            println("No hay series")
        } else {
            series.forEach { println(it) }
        }
    }

    fun createSerie() {
        println("Ingrese el título de la serie:")
        val title = readln()
        println("Ingrese el género de la serie:")
        val genre = readln()
        println("¿La serie ya está finalizada? (s/n)")
        val isFinished = readln().lowercase() == "s"
        println("Ingrese el número de temporadas de la serie:")
        val seasons = readln().toInt()
        println("Ingrese la fecha de emision de la serie (formato: yyyy-MM-dd):")
        val emissionDate = LocalDate.parse(readln())
        println("Selecciona el servicio de streaming de la serie:")
        val streamingServices = StreamingServiceService.getInstance().getAll()
        streamingServices.forEachIndexed { index, streamingService ->
            println("${index + 1}. $streamingService")
        }
        val streamingServiceIndex = readln().toInt() - 1
        val streamingService = streamingServices[streamingServiceIndex]

        val series = SeriesDto(
            title = title,
            genre = genre,
            isFinished = isFinished,
            seasons = seasons,
            emissionDate = emissionDate,
            streamingService = streamingService,
        )

        val createdSeries = SeriesService.getInstance().create(series)
        val formattedData = tables.createTableFromList(createdSeries.getListOfStringFromData())
        println(formattedData)
        println("Serie creada correctamente")
    }
}