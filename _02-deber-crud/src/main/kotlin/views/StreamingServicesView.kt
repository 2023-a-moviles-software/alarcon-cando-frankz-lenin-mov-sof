package views

import dtos.StreamingServiceDto
import models.Serie
import models.StreamingService
import services.StreamingServiceService

class StreamingServicesView {

    private val tables = ConsoleTable()

    fun selectStreamingServicesMenu(parent: MainView) {
        var goBack = false
        do {
            showStreamingServicesMenu()
            val option = readln().toInt()
            when (option) {
                1 -> listStreamingServices()
                2 -> createStreamingService()
                3 -> updateStreamingService()
                4 -> deleteStreamingService()
                5 -> {
                    goBack = true
                    parent.init()
                }
                else -> println("Opción no válida")
            }
        } while (!goBack)
    }

    fun showStreamingServicesMenu() {
        println("Seleccione una opción:")
        println("1. Listar servicios de streaming")
        println("2. Crear servicio de streaming")
        println("3. Actualizar servicio de streaming")
        println("4. Eliminar servicio de streaming")
        println("5. Volver atrás")
    }

    private fun listStreamingServices() {
        val streamingServices = StreamingServiceService.getInstance().safeGetAll()
        if (streamingServices.isEmpty()) {
            println("No hay servicios de streaming")
        } else {
            streamingServices.forEach { println(tables.createTableFromList(it.getListOfStringFromData())) }
        }
    }

    private fun createStreamingService() {
        println("Ingrese el nombre del servicio de streaming:")
        val name = readln()
        println("Ingrese la descripción del servicio de streaming:")
        val description = readln()
        println("Ingrese el precio del servicio de streaming:")
        val price = readln().toDouble()

        val streamingService = StreamingServiceDto(
            name = name,
            description = description,
            price = price,
            series = listOf<Serie>()
        )

        val createdStreamingService = StreamingServiceService.getInstance().create(streamingService)
        val formattedData = tables.createTableFromList(createdStreamingService.getListOfStringFromData())
        println(formattedData)
        println("Servicio de Streaming creado con éxito")
    }

    private fun updateStreamingService() {
        val streamingServices = StreamingServiceService.getInstance().safeGetAll()
        if (streamingServices.isEmpty()) {
            println("No hay servicios de streaming")
            return
        }
        streamingServices.forEachIndexed { index, it -> println("${index + 1}. ${it.getName()}") }
        println("Selecciona el servicio de streaming que deseas actualizar:")
        val option = readln().toInt()
        if (option > streamingServices.size || option < 1) {
            println("Opción no válida")
            return
        }
        val selectedStreamingService = streamingServices[option - 1]
        println(tables.createTableFromList(selectedStreamingService.getListOfStringFromData()))
        println("Ingrese el nuevo nombre del servicio de streaming:")
        val name = readln()
        println("Ingrese la nueva descripción del servicio de streaming:")
        val description = readln()
        println("Ingrese el nuevo precio del servicio de streaming:")
        val price = readln().toDouble()

        val streamingService = StreamingService(
            id = selectedStreamingService.getId(),
            name = name,
            description = description,
            price = price,
            series = selectedStreamingService.getSeries()
        )

        val updatedStreamingService = StreamingServiceService.getInstance().update(streamingService)
        if (updatedStreamingService == null) {
            println("No se pudo actualizar el servicio de streaming")
            return
        }
        val formattedData = tables.createTableFromList(updatedStreamingService.getListOfStringFromData())
        println(formattedData)
        println("Servicio de Streaming actualizado con éxito")
    }

    private fun deleteStreamingService() {
        val streamingServices = StreamingServiceService.getInstance().safeGetAll()
        if (streamingServices.isEmpty()) {
            println("No hay servicios de streaming")
            return
        }
        streamingServices.forEachIndexed { index, it -> println("${index + 1}. ${it.getName()}") }
        println("Selecciona el servicio de streaming que deseas eliminar:")
        val option = readln().toInt()
        if (option > streamingServices.size || option < 1) {
            println("Opción no válida")
            return
        }
        val selectedStreamingService = streamingServices[option - 1]
        if (selectedStreamingService.getSeries().isNotEmpty()) {
            println("No se puede eliminar el servicio de streaming porque tiene series asociadas")
            return
        }
        val id = selectedStreamingService.getId()
        StreamingServiceService.getInstance().remove(id)
        println("Servicio de Streaming eliminado con éxito")
    }
}