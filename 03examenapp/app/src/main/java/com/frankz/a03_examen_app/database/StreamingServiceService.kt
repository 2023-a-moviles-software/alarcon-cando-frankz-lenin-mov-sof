package com.frankz.a03_examen_app.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService

class StreamingServiceService(
    context: Context?
): SQLiteOpenHelper(
    context,
    "streaming_service",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCreate = """
            CREATE TABLE STREAMING_SERVICE(
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                NAME VARCHAR(50),
                DESCRIPTION VARCHAR(50),
                PRICE DOUBLE                
            )
        """.trimIndent()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun getAll() {
        val dbReadable = readableDatabase

        val query = "SELECT * FROM STREAMING_SERVICE"

        val result = dbReadable.rawQuery(query, null)

        val streamingServices = arrayListOf<StreamingService>()

        if (result.moveToFirst()) {
            do {
                val id = result.getInt(0)
                val name = result.getString(1)
                val description = result.getString(2)
                val price = result.getDouble(3)

                val streamingService = StreamingService(id, name, description, price, mutableListOf<Series>())

                streamingServices.add(streamingService)
            } while (result.moveToNext())
        }
    }
}