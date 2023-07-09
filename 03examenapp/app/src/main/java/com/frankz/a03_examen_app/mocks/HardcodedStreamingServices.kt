package com.frankz.a03_examen_app.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService

class HardcodedStreamingServices {
    companion object {
        val streamingServices = arrayListOf<StreamingService>()
        @RequiresApi(Build.VERSION_CODES.O)
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
            }

        init {
            streamingServices.add(
                StreamingService(
                    1,
                    "Netflix",
                    "Netflix, Inc. es una empresa comercial estadounidense de entretenimiento que proporciona mediante tarifa plana mensual streaming (flujo) multimedia (principalmente, películas, series de televisión, documentales y películas de animación) bajo demanda por Internet y de DVD-por correo, donde los DVD se envían mediante Permit Reply Mail.",
                    9.99,
                    mutableListOf()
                )
            )
            streamingServices.add(
                StreamingService(
                    2,
                    "Amazon Prime Video",
                    "Prime Video, también comercializado como Amazon Prime Video, es un servicio de vídeo bajo demanda creado, propiedad y operado por Amazon. Ofrece programas de televisión y películas para alquilar o comprar. La mayoría de los programas de Amazon se pueden ver sin costo adicional con una membresía de Amazon Prime.",
                    5.99,
                    mutableListOf()
                )
            )
            streamingServices.add(
                StreamingService(
                    3,
                    "Disney+",
                    "Disney+ es un servicio de suscripción de video a pedido por Internet propiedad y operado por la división Direct-to-Consumer & International de The Walt Disney Company. El servicio ofrece principalmente películas y series de televisión producidas por Walt Disney Studios y Walt Disney Television, con el servicio de distribución de contenido de Disney, Brand.",
                    7.99,
                    mutableListOf()
                )
            )
            streamingServices.add(
                StreamingService(
                    4,
                    "HBO Max",
                    "HBO Max es un servicio de video bajo demanda por suscripción estadounidense propiedad de WarnerMedia Entertainment, una división de WarnerMedia. El servicio fue lanzado el 27 de mayo de 2020.​ HBO Max es basado en HBO, un servicio de suscripción de televisión por cable que ha operado desde 1972, y su servicio de streaming OTT HBO Go, que fue lanzado en 2010.",
                    14.99,
                    mutableListOf()
                )
            )
            streamingServices.add(
                StreamingService(
                    5,
                    "Paramount+",
                    "Paramount+ es un servicio de streaming de video por suscripción estadounidense, propiedad y operado por ViacomCBS Streaming, una división de ViacomCBS.​ El servicio ofrece contenido original, contenido recientemente emitido en las propiedades de CBS y ViacomCBS, y contenido de la biblioteca de ViacomCBS",
                    9.99,
                    mutableListOf()
                )
            )
        }
    }
}