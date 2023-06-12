package views

import services.SeriesService
import services.StreamingServiceService

class MainView {
    private val tables = ConsoleTable()
    private val seriesView = SeriesView()
    private val streamingServiceView = StreamingServicesView()

    fun init() {
        header()
        selectEntitiesMenu()
    }

    fun header() {
        val tableWithTitle = tables.createTableWithText("Bienvenido a la Aplicación de Gestión de Series")
        println(tableWithTitle)
    }

    fun selectEntitiesMenu() {
        var showAgain = false
        do {
            println("Seleccione una opción:")
            println("1. Series")
            println("2. Streaming Services")
            println("3. Salir")

            val option = readln().toInt()
            when (option) {
                1 -> {
                    if (StreamingServiceService.getInstance().safeGetAll().isEmpty()) {
                        println("No existen Servicios de Streaming, primero crea uno.")
                        showAgain = true
                    } else {
                        seriesView.selectSeriesMenu(this)
                    }
                }
                2 -> streamingServiceView.selectStreamingServicesMenu(this)
                3 -> println("Hasta pronto!")
                else -> println("Opción no válida")
            }
        } while (showAgain)

    }


}