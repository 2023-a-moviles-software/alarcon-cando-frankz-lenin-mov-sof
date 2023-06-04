package views

import dtos.StreamingServiceDto
import models.Serie
import services.StreamingServiceService
import java.time.LocalDate

class ConsoleView {
    fun init() {
        header()
        showSelectEntities()
        //    println(SeriesService.getInstance().getAll())
//            println(
//                StreamingServiceService.getInstance().create(
//                StreamingServiceDto("name", "description", 5.0, listOf(
//                    Serie("title", "genre", "description", true, 5, LocalDate.parse("2021-09-01"), StreamingServiceService.getInstance().getOne("kkkn")!!),
//                    Serie("title", "genre", "description", true, 5, LocalDate.parse("2021-09-01"), StreamingServiceService.getInstance().getOne("kkkn")!!),
//                ))
//            ))
        //    println(
        //        SerieService.getInstance().create(
        //            SerieDto("title", "genre", true, 5, LocalDate.parse("2021-09-01"),
        //                StreamingServiceService.getInstance().getOne(1)!!
        //        )))
    }

    fun header() {
        println("Welcome to the streaming service manager")
    }

    fun showSelectEntities() {
        println("1. Series")
        println("2. Streaming Services")
    }
}