package services

import dtos.StreamingServiceDto
import models.Serie
import models.StreamingService
import java.io.File

class StreamingServiceService {
    private val file: File = File("src/main/resources/streamingServices.txt").also {
        if (!it.exists()) {
            it.createNewFile()
        }
    }

    companion object {
        private var instance: StreamingServiceService? = null;

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: StreamingServiceService().also { instance = it }
        }
    }

    private fun randomString(): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..10)
            .map { chars.random() }
            .joinToString("")
    }


    fun getAll(): List<StreamingService> {
        val lines = file.readLines()
        val streamingServices = lines.map { it ->
            val streamingServiceSplit = it.split(",")
            // get series from an array of ids
            val series = streamingServiceSplit[4].split(";")
                .map { SeriesService.getInstance().getOne(it) ?: return listOf<StreamingService>() }
            return@map StreamingService(
                streamingServiceSplit[0],
                streamingServiceSplit[1],
                streamingServiceSplit[2],
                streamingServiceSplit[3].toDouble(),
                series
            )
        }
        return streamingServices
    }

    fun getOne(id: String): StreamingService? {
        if (id == "1") return null
        return StreamingService("dsfds", "name", "description", 5.0, listOf<Serie>())
    }

    fun create(streamingService: StreamingServiceDto): StreamingService {
        val newStreamingService = StreamingService(
            randomString(),
            streamingService.name,
            streamingService.description,
            streamingService.price,
            streamingService.series
        )
        file.appendText(newStreamingService.toString() + "\n")
        return newStreamingService
    }

    fun update(streamingService: StreamingService) {
        println("StreamingService updated")
    }

    fun remove(id: Int) {
        println("StreamingService removed")
    }
}