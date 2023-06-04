import dtos.SerieDto
import dtos.StreamingServiceDto
import models.Serie
import services.SeriesService
import services.StreamingServiceService
import java.text.DateFormat
import java.time.LocalDate
import java.util.Date

/*
*
Escribir un programa CRUD (Create Read Update Delete). Estas operaciones deben de realizarse en las dos entidades.
Las entidades deben de tener 5 datos cada una
* Ej: Nombre. Entre los 10 datos de las entidades deben de haber las siguientes variables diferentes de Java
* EJ: Fecha,Booleano, String, Entero, Decimal. Las entidades estan relacionadas de UNO a MUCHOS.
* En el ejemplo 1 RECETA contiene un arreglo de INGREDIENTES.
Ej:
Receta 1
5 datos
Nombre (str)
Numero Total de ingredientes (int)
etc
Ingrediente N
5 datos
Toda la información se va a guardar en ARCHIVOS
El deber se entrega con un video (Crear Leer Actualizar Delete) y el codigo escrito en el repositorio del curso
*
*/

fun main(args: Array<String>) {
//    println(SeriesService.getInstance().getAll())
    println(StreamingServiceService.getInstance().create(
        StreamingServiceDto("name", "description", 5.0, listOf(
            Serie("title", "genre", "description", true, 5, LocalDate.parse("2021-09-01"), StreamingServiceService.getInstance().getOne("kkkn")!!),
            Serie("title", "genre", "description", true, 5, LocalDate.parse("2021-09-01"), StreamingServiceService.getInstance().getOne("kkkn")!!),
        ))
    ))
//    println(
//        SerieService.getInstance().create(
//            SerieDto("title", "genre", true, 5, LocalDate.parse("2021-09-01"),
//                StreamingServiceService.getInstance().getOne(1)!!
//        )))
}