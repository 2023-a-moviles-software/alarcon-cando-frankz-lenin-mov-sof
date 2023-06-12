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
                3 -> updateSerie()
                4 -> deleteSerie()
                5 -> {
                    goBack = true
                    parent.init()
                }
                else -> println("Opción no válida")
            }
        } while (!goBack)
    }

    fun listAllSeries() {
        val series: List<Serie> = SeriesService.getInstance().safeGetAll()

        if (series.isEmpty()) {
            println("No hay series")
        } else {
            series.forEach { println(tables.createTableFromList(it.getListOfStringFromData())) }
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
        val streamingServices = StreamingServiceService.getInstance().safeGetAll()
        streamingServices.forEachIndexed { index, streamingService ->
            println("${index + 1}. ${streamingService.getName()}")
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
        streamingService.addSeries(createdSeries)
        StreamingServiceService.getInstance().update(streamingService)
        val formattedData = tables.createTableFromList(createdSeries.getListOfStringFromData())
        println(formattedData)
        println("Serie creada correctamente")
    }

    fun updateSerie() {

    }

    fun deleteSerie() {
        val series = SeriesService.getInstance().safeGetAll()
        if (series.isEmpty()) {
            println("No hay series")
            return
        }
        series.forEachIndexed { index, it -> println("${index + 1}. ${it.getTitle()}") }
        println("Selecciona la serie que deseas eliminar:")
        val option = readln().toInt()
        if (option > series.size || option < 1) {
            println("Opción no válida")
            return
        }
        val selectedSeries = series[option - 1]
        val streamingService = selectedSeries.getStreamingService()
        streamingService.removeSeries(selectedSeries)
        StreamingServiceService.getInstance().update(streamingService)
        SeriesService.getInstance().remove(selectedSeries.getId())
        println("Serie eliminada con éxito")
    }
}