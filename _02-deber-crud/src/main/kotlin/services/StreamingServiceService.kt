package services

import models.Serie
import models.StreamingService

class StreamingServiceService {
    companion object {
        private var instance: StreamingServiceService? = null;

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: StreamingServiceService().also { instance = it }
        }
    }

    public fun getAll(): List<StreamingService> {
        return listOf<StreamingService>()
    }

    public fun getOne(id: Int): StreamingService {
        return StreamingService("", "name", "description", 5.0, listOf<Serie>())
    }

    public fun create(streamingService: StreamingService) {
        println("StreamingService created")
    }

    public fun update(streamingService: StreamingService) {
        println("StreamingService updated")
    }

    public fun remove(id: Int) {
        println("StreamingService removed")
    }
}