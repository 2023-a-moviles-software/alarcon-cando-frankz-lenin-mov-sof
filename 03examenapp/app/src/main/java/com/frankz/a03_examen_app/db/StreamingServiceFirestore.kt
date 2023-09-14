package com.frankz.a03_examen_app.db


import com.frankz.a03_examen_app.dtos.StreamingServiceDto
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StreamingServiceFirestore {
    val db = Firebase.firestore

    companion object {
        fun createStreamingServiceFromDocument(document: QueryDocumentSnapshot): StreamingService {
            val id = document.id
            val name = document.data["name"] as String?
            val description = document.data["description"] as String?
            val price = document.data["price"] as Double?
            val series = mutableListOf<Series>()

            if (id == null || name == null || description == null || price == null) {
                return StreamingService()
            }

            return StreamingService(id, name, description, price, series)
        }

        fun createStreamingServiceFromDocument(document: DocumentSnapshot): StreamingService {
            val id = document.id
            val name = document.data?.get("name") as String?
            val description = document.data?.get("description") as String?
            val price = document.data?.get("price") as Double?
            val series = mutableListOf<Series>()

            if (id == null || name == null || description == null || price == null) {
                return StreamingService()
            }

            return StreamingService(id, name, description, price, series)
        }
    }


    fun getAll(): Task<QuerySnapshot> {
        val streamingServices = arrayListOf<StreamingService>()
        return db.collection("streaming_services")
            .get()
    }


    fun getOne(id: String): Task<DocumentSnapshot> {
        return db.collection("streaming_services")
            .document(id)
            .get()
    }

    fun create(streamingService: StreamingServiceDto) {
        val streamingServiceData = hashMapOf(
            "name" to streamingService.getName(),
            "description" to streamingService.getDescription(),
            "price" to streamingService.getPrice(),
        )

        db.collection("streaming_services")
            .add(streamingServiceData)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }

    fun update(id: String, streamingService: StreamingServiceDto) {
        val streamingServiceData = hashMapOf(
            "name" to streamingService.getName(),
            "description" to streamingService.getDescription(),
            "price" to streamingService.getPrice(),
        )

        db.collection("streaming_services")
            .document(id)
            .set(streamingServiceData)
            .addOnSuccessListener {
                println("DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                println("Error updating document: $e")
            }
    }

    fun remove(id: String) {
        db.collection("streaming_services")
            .document(id)
            .delete()
            .addOnSuccessListener {
                println("DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                println("Error deleting document: $e")
            }
    }
}