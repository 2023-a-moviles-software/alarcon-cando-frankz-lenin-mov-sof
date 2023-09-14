package com.frankz.a03_examen_app.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.dtos.StreamingServiceDto
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService

class SqliteHelperStreamingService(
    context: Context?
): SQLiteOpenHelper(
    context,
    "streaming_services",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCreateStreamingServiceTable =
            """
                CREATE TABLE STREAMING_SERVICE(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME VARCHAR(60),
                    DESCRIPTION VARCHAR(200),                    
                    PRICE REAL
                )
            """.trimIndent()

        db?.execSQL(scriptCreateStreamingServiceTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Code
    }

//    fun getAll(): ArrayList<StreamingService> {
//        val readableDatabase = readableDatabase
//
//        val script = "SELECT * FROM STREAMING_SERVICE".trimIndent()
//        val result = readableDatabase.rawQuery(
//            script,
//            null
//        )
//        // logica busqueda
//        val exists = result.moveToFirst()
//
//        val streamingServices = arrayListOf<StreamingService>()
//        if (exists) {
//            do {
//                val id = result.getInt(0) // Columna indice 0 -> ID
//                val name = result.getString(1) // Columna indice 1 -> NAME
//                val description = result.getString(2) // Columna indice 2 -> DESCRIPTION
//                val price = result.getDouble(3) // Columna indice 3 -> PRICE
//
//                if (id != null) {
//                    val streamingService = StreamingService(id, name, description, price, mutableListOf<Series>())
//                    streamingServices.add(streamingService)
//                }
//            } while (result.moveToNext())
//        }
//
//        result.close()
//        readableDatabase.close()
//
//        return streamingServices
//    }


//    fun getOne(id: Int): StreamingService {
//        val readableDatabase = readableDatabase
//
//        val script = "SELECT * FROM STREAMING_SERVICE WHERE ID = ?".trimIndent()
//        val result = readableDatabase.rawQuery(
//            script,
//            arrayOf(id.toString())
//        )
//        // logica busqueda
//        val exists = result.moveToFirst()
//
//        val found = StreamingService(0, "", "", 0.0, mutableListOf<Series>())
//        if (exists) {
//            do {
//                val id = result.getInt(0) // Columna indice 0 -> ID
//                val name = result.getString(1) // Columna indice 1 -> NAME
//                val description = result.getString(2) // Columna indice 2 -> DESCRIPTION
//                val price = result.getDouble(3) // Columna indice 3 -> PRICE
//
//                if (id != null) {
//                    found.setId(id)
//                    found.setName(name)
//                    found.setDescription(description)
//                    found.setPrice(price)
//                }
//            } while (result.moveToNext())
//        }
//
//
//        result.close()
//        readableDatabase.close()
//
//        return found
//    }

//    fun create(data: StreamingServiceDto): Boolean {
//        val writableDatabase = writableDatabase
//
//        val values = ContentValues()
//
//        values.put("NAME", data.getName())
//        values.put("DESCRIPTION", data.getDescription())
//        values.put("PRICE", data.getPrice())
//
//        val result = writableDatabase.insert(
//            "STREAMING_SERVICE",
//            null,
//            values
//        )
//
//        writableDatabase.close()
//
//        return result.toInt() != -1
//    }

//    fun update(id: Int, data: StreamingServiceDto): Boolean {
//        val writableDatabase = writableDatabase
//
//        val values = ContentValues()
//
//        values.put("NAME", data.getName())
//        values.put("DESCRIPTION", data.getDescription())
//        values.put("PRICE", data.getPrice())
//
//        val result = writableDatabase.update(
//            "STREAMING_SERVICE",
//            values,
//            "ID = ?",
//            arrayOf(id.toString())
//        )
//
//        writableDatabase.close()
//
//        return result != -1
//    }

//    fun remove(id: Int): Boolean {
//        val writableDatabase = writableDatabase
//
//        val result = writableDatabase.delete(
//            "STREAMING_SERVICE",
//            "ID = ?",
//            arrayOf(id.toString())
//        )
//
//        writableDatabase.close()
//
//        return result != -1
//    }
}