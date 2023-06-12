package views

import dtos.StreamingServiceDto
import models.Serie
import services.SeriesService
import services.StreamingServiceService
import java.time.LocalDate

class ConsoleView {
    private val tables = ConsoleTable()

    fun init() {
        header()
        selectEntitiesMenu()
    }

    fun header() {
        val tableWithTitle = tables.createTableWithText("Bienvenido a la Aplicación de Gestión de Series")
        println(tableWithTitle)
    }

    fun selectEntitiesMenu() {
        println("Seleccione una opción:")
        println("1. Series")
        println("2. Streaming Services")
        println("3. Salir")

        val option = readln().toInt()
        when (option) {
            1 -> selectSeriesMenu()
            2 -> selectStreamingServicesMenu()
            3 -> println("Hasta pronto!")
            else -> println("Opción no válida")
        }
    }

    fun selectSeriesMenu() {
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
                1 -> SeriesService.getInstance().getAll().forEach { println(it) }
                2 -> SeriesService.getInstance().getAll().forEach { println(it) }
                3 -> SeriesService.getInstance().getAll().forEach { println(it) }
                4 -> SeriesService.getInstance().getAll().forEach { println(it) }
                5 -> goBack = true
                else -> println("Opción no válida")
            }
        } while (!goBack)
    }

    fun selectStreamingServicesMenu() {
        println("Seleccione una opción:")
        println("1. Listar servicios de streaming")
        println("2. Crear servicio de streaming")
        println("3. Actualizar servicio de streaming")
        println("4. Eliminar servicio de streaming")
        println("5. Volver atrás")
    }
}