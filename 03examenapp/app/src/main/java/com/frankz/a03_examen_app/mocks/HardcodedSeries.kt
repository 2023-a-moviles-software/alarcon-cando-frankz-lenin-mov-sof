package com.frankz.a03_examen_app.mocks

import android.os.Build
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService
import java.time.LocalDate

class HardcodedSeries {
    @RequiresApi(Build.VERSION_CODES.O)
    companion object {
        val series = arrayListOf<Series>()
        private val streamingServices = HardcodedStreamingServices.streamingServices
            .map { it ->
                return@map StreamingService(
                    it.getId(),
                    it.getName(),
                    it.getDescription(),
                    it.getPrice(),
                    mutableListOf()
                )
            }
        init {
            series.add(
                Series(
                    1,
                    "The Witcher",
                    "Fantasía",
                    false,
                    3,
                    LocalDate.parse("2019-12-20"),
                    streamingServices[0]
                )
            )
            series.add(
                Series(
                    2,
                    "The Mandalorian",
                    "Ciencia ficción",
                    false,
                    2,
                    LocalDate.parse("2019-11-12"),
                    streamingServices[2]
                )
            )
            series.add(
                Series(
                    3,
                    "The Boys",
                    "Superhéroes",
                    false,
                    2,
                    LocalDate.parse("2019-07-26"),
                    streamingServices[1]
                )
            )
            series.add(
                Series(
                    4,
                    "The Walking Dead",
                    "Terror",
                    true,
                    10,
                    LocalDate.parse("2010-10-31"),
                    streamingServices[0]
                )
            )
            series.add(
                Series(
                    5,
                    "The Big Bang Theory",
                    "Comedia",
                    true,
                    12,
                    LocalDate.parse("2007-09-24"),
                    streamingServices[0]
                )
            )
            series.add(
                Series(
                    6,
                    "Friends",
                    "Comedia",
                    true,
                    10,
                    LocalDate.parse("1994-09-22"),
                    streamingServices[0]
                )
            )
            series.add(
                Series(
                    7,
                    "Breaking Bad",
                    "Drama",
                    true,
                    5,
                    LocalDate.parse("2008-01-20"),
                    streamingServices[0]
                )
            )
            series.add(
                Series(
                    8,
                    "Avengers: What If...?",
                    "Animación",
                    false,
                    1,
                    LocalDate.parse("2022-03-24"),
                    streamingServices[2]
                )
            )
        }
    }

}