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
                .map { SeriesService.getInstance().getOne(it) }

            val validSeries = series.filterNotNull()
            return@map StreamingService(
                streamingServiceSplit[0],
                streamingServiceSplit[1],
                streamingServiceSplit[2],
                streamingServiceSplit[3].toDouble(),
                validSeries.toMutableList()
            )
        }
        return streamingServices
    }

    fun safeGetAll(): List<StreamingService> {
        val lines = file.readLines()
        val streamingServices = lines.map { it ->
            val streamingServiceSplit = it.split(",")
            // get series from an array of ids
            val series = streamingServiceSplit[4].split(";")
                .map { SeriesService.getInstance().getOneWithoutStreamingService(it) }

            val validSeries = series.filterNotNull()
            return@map StreamingService(
                streamingServiceSplit[0],
                streamingServiceSplit[1],
                streamingServiceSplit[2],
                streamingServiceSplit[3].toDouble(),
                validSeries.toMutableList()
            )
        }
        return streamingServices
    }

    fun getOne(id: String): StreamingService? {
        val lines = file.readLines()
        val streamingServiceString = lines.find { it.split(",")[0] == id } ?: return null

        val streamingServiceSplit = streamingServiceString.split(",")
        // get series from an array of ids
        val series = streamingServiceSplit[4].split(";")
            .map { SeriesService.getInstance().getOne(it) }

        val validSeries = series.filterNotNull()

        return StreamingService(
            streamingServiceSplit[0],
            streamingServiceSplit[1],
            streamingServiceSplit[2],
            streamingServiceSplit[3].toDouble(),
            validSeries.toMutableList()
        )
    }

    fun getOneWithoutSeries(id: String): StreamingService? {
        val lines = file.readLines()
        val streamingServiceString = lines.find { it.split(",")[0] == id } ?: return null

        val streamingServiceSplit = streamingServiceString.split(",")
        // get series from an array of ids
        val series = streamingServiceSplit[4].split(";")
            .map { SeriesService.getInstance().getOneWithoutStreamingService(it) }

        val validSeries = series.filterNotNull()

        return StreamingService(
            streamingServiceSplit[0],
            streamingServiceSplit[1],
            streamingServiceSplit[2],
            streamingServiceSplit[3].toDouble(),
            validSeries.toMutableList()
        )
    }

    fun create(streamingService: StreamingServiceDto): StreamingService {
        val newStreamingService = StreamingService(
            randomString(),
            streamingService.name,
            streamingService.description,
            streamingService.price,
            streamingService.series.toMutableList()
        )
        file.appendText(newStreamingService.toString() + "\n")
        return newStreamingService
    }

    fun update(streamingService: StreamingService): StreamingService? {
        val lines = file.readLines()
        val streamingServiceString = lines.find { it.split(",")[0] == streamingService.getId() } ?: return null

        val streamingServiceSplit = streamingServiceString.split(",")

        val newStreamingService = StreamingService(
            streamingServiceSplit[0],
            streamingService.getName(),
            streamingService.getDescription(),
            streamingService.getPrice(),
            streamingService.getSeries()
        )

        this.remove(streamingService.getId())

        file.appendText(newStreamingService.toString() + "\n")

        return newStreamingService
    }

    fun remove(id: String) {
        file.readLines()
            .filter { it -> it.split(",")[0] != id }
            .joinToString("\n", postfix = "\n")
            .also { file.writeText(it) }
    }
}