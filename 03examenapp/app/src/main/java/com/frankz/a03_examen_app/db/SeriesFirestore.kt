package com.frankz.a03_examen_app.db

import com.frankz.a03_examen_app.dtos.SeriesDto
import com.frankz.a03_examen_app.models.Series
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SeriesFirestore {
    val db = Firebase.firestore

    companion object {
        fun createSeriesFromDocument(document: QueryDocumentSnapshot): Series {
            val id = document.id
            val title = document.data["title"] as String?
            val genre = document.data["genre"] as String?
            val isFinished = document.data["isFinished"] as Boolean?
            val seasons = document.data["seasons"] as Number?
            val emissionDate = document.data["emissionDate"] as String?
            val streamingServiceId = document.data["streamingServiceId"] as String?

            if (id == null || title == null || genre == null || isFinished == null || seasons == null || emissionDate == null || streamingServiceId == null) {
                return Series()
            }

            return Series(id, title, genre, isFinished, seasons.toInt(), emissionDate, streamingServiceId)
        }

        fun createSeriesFromDocument(document: DocumentSnapshot): Series {
            val id = document.id
            val title = document.data?.get("title") as String?
            val genre = document.data?.get("genre") as String?
            val isFinished = document.data?.get("isFinished") as Boolean?
            val seasons = document.data?.get("seasons") as Int?
            val emissionDate = document.data?.get("emissionDate") as String?
            val streamingServiceId = document.data?.get("streamingServiceId") as String?

            if (id == null || title == null || genre == null || isFinished == null || seasons == null || emissionDate == null || streamingServiceId == null) {
                return Series()
            }

            return Series(id, title, genre, isFinished, seasons, emissionDate, streamingServiceId)
        }
    }



    fun getAllByStreamingService(streamingServiceId: String): Task<QuerySnapshot> {
        return db.collection("series")
            .whereEqualTo("streamingServiceId", streamingServiceId)
            .get()
    }

    fun create(data: SeriesDto): Boolean {
        val seriesData = hashMapOf(
            "title" to data.getTitle(),
            "genre" to data.getGenre(),
            "isFinished" to data.getIsFinished(),
            "seasons" to data.getSeasons(),
            "emissionDate" to data.getEmissionDate(),
            "streamingServiceId" to data.getStreamingServiceId()
        )
        var result = false
        db.collection("series")
            .add(seriesData)
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }
        return result
    }

    fun update(id: String, data: SeriesDto): Boolean {
        val seriesData = hashMapOf(
            "title" to data.getTitle(),
            "genre" to data.getGenre(),
            "isFinished" to data.getIsFinished(),
            "seasons" to data.getSeasons(),
            "emissionDate" to data.getEmissionDate(),
            "streamingServiceId" to data.getStreamingServiceId()
        )
        var result = false
        db.collection("series")
            .document(id)
            .set(seriesData)
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }
        return result
    }

    fun remove(id: String): Boolean {
        var result = false
        db.collection("series")
            .document(id)
            .delete()
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }
        return result
    }
}