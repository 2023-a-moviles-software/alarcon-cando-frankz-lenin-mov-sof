package com.frankz.a03_examen_app.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService
import java.time.LocalDate

class HardcodedStreamingServices {
    @RequiresApi(Build.VERSION_CODES.O)
    companion object {
        val streamingServices = arrayListOf<StreamingService>()
        /*@RequiresApi(Build.VERSION_CODES.O)
        val series = HardcodedSeries.series
            .map { it ->
                return@map Series(
                    it.getId(),
                    it.getTitle(),
                    it.getGenre(),
                    it.getIsFinished(),
                    it.getSeasons(),
                    it.getEmissionDate(),
                    StreamingService()
                )
            }*/

        //generate random string id
        fun generateId(): String {
            return (0..10).map { (('a'..'z') + ('A'..'Z') + ('0'..'9')).random() }.joinToString("")
        }

        init {
            streamingServices.add(
                StreamingService(
                    generateId(),
                    "Netflix",
                    "Netflix, Inc. es una empresa comercial estadounidense de entretenimiento que proporciona mediante tarifa plana mensual streaming (flujo) multimedia (principalmente, películas, series de televisión, documentales y películas de animación) bajo demanda por Internet y de DVD-por correo, donde los DVD se envían mediante Permit Reply Mail.",
                    9.99,
                    mutableListOf(
                        Series(
                            generateId(),
                            "The Witcher",
                            "Fantasía",
                            false,
                            3,
                            LocalDate.parse("2019-12-20"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "The Walking Dead",
                            "Terror",
                            true,
                            10,
                            LocalDate.parse("2010-10-31"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "The Big Bang Theory",
                            "Comedia",
                            true,
                            12,
                            LocalDate.parse("2007-09-24"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "Friends",
                            "Comedia",
                            true,
                            10,
                            LocalDate.parse("1994-09-22"),
                            StreamingService()
                        )
                    )
                )
            )
            streamingServices.add(
                StreamingService(
                    generateId(),
                    "Amazon Prime Video",
                    "Prime Video, también comercializado como Amazon Prime Video, es un servicio de vídeo bajo demanda creado, propiedad y operado por Amazon. Ofrece programas de televisión y películas para alquilar o comprar. La mayoría de los programas de Amazon se pueden ver sin costo adicional con una membresía de Amazon Prime.",
                    5.99,
                    mutableListOf(
                        Series(
                            generateId(),
                            "The Boys",
                            "Superhéroes",
                            false,
                            2,
                            LocalDate.parse("2019-07-26"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "Mr. Robot",
                            "Drama",
                            true,
                            4,
                            LocalDate.parse("2015-06-24"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "The Good Doctor",
                            "Drama",
                            false,
                            3,
                            LocalDate.parse("2017-09-25"),
                            StreamingService()
                        ),
                    )
                )
            )
            streamingServices.add(
                StreamingService(
                    generateId(),
                    "Disney+",
                    "Disney+ es un servicio de suscripción de video a pedido por Internet propiedad y operado por la división Direct-to-Consumer & International de The Walt Disney Company. El servicio ofrece principalmente películas y series de televisión producidas por Walt Disney Studios y Walt Disney Television, con el servicio de distribución de contenido de Disney, Brand.",
                    7.99,
                    mutableListOf(
                        Series(
                            generateId(),
                            "The Mandalorian",
                            "Ciencia ficción",
                            false,
                            2,
                            LocalDate.parse("2019-11-12"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "WandaVision",
                            "Superhéroes",
                            false,
                            1,
                            LocalDate.parse("2021-01-15"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "The Falcon and the Winter Soldier",
                            "Superhéroes",
                            false,
                            1,
                            LocalDate.parse("2021-03-19"),
                            StreamingService()
                        ),
                    )
                )
            )
            streamingServices.add(
                StreamingService(
                    generateId(),
                    "HBO Max",
                    "HBO Max es un servicio de video bajo demanda por suscripción estadounidense propiedad de WarnerMedia Entertainment, una división de WarnerMedia. El servicio fue lanzado el 27 de mayo de 2020.​ HBO Max es basado en HBO, un servicio de suscripción de televisión por cable que ha operado desde 1972, y su servicio de streaming OTT HBO Go, que fue lanzado en 2010.",
                    14.99,
                    mutableListOf(
                        Series(
                            generateId(),
                            "Game of Thrones",
                            "Fantasía",
                            true,
                            8,
                            LocalDate.parse("2011-04-17"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "Westworld",
                            "Ciencia ficción",
                            false,
                            3,
                            LocalDate.parse("2016-10-02"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "The Sopranos",
                            "Drama",
                            true,
                            6,
                            LocalDate.parse("1999-01-10"),
                            StreamingService()
                        ),
                    )
                )
            )
            streamingServices.add(
                StreamingService(
                    generateId(),
                    "Paramount+",
                    "Paramount+ es un servicio de streaming de video por suscripción estadounidense, propiedad y operado por ViacomCBS Streaming, una división de ViacomCBS.​ El servicio ofrece contenido original, contenido recientemente emitido en las propiedades de CBS y ViacomCBS, y contenido de la biblioteca de ViacomCBS",
                    9.99,
                    mutableListOf(
                        Series(
                            generateId(),
                            "Star Trek: Picard",
                            "Ciencia ficción",
                            false,
                            1,
                            LocalDate.parse("2020-01-23"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "Star Trek: Discovery",
                            "Ciencia ficción",
                            false,
                            3,
                            LocalDate.parse("2017-09-24"),
                            StreamingService()
                        ),
                        Series(
                            generateId(),
                            "Star Trek: Lower Decks",
                            "Ciencia ficción",
                            false,
                            1,
                            LocalDate.parse("2020-08-06"),
                            StreamingService()
                        ),
                    )
                )
            )
        }
    }
}