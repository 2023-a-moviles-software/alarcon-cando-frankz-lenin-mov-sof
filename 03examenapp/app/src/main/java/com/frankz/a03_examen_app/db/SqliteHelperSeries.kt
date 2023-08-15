package com.frankz.a03_examen_app.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.frankz.a03_examen_app.dtos.SeriesDto
import com.frankz.a03_examen_app.models.Series

class SqliteHelperSeries(
    context: Context?
): SQLiteOpenHelper(
    context,
    "series",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCreateStreamingServiceTable =
            """
                CREATE TABLE SERIES(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME VARCHAR(60),
                    GENRE VARCHAR(60),
                    IS_FINISHED INTEGER,
                    SEASONS INTEGER,
                    EMISSION_DATE VARCHAR(100),
                    STREAMING_SERVICE_ID INTEGER,
                    FOREIGN KEY(STREAMING_SERVICE_ID) REFERENCES STREAMING_SERVICE(ID)
                )
            """.trimIndent()

        db?.execSQL(scriptCreateStreamingServiceTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Code
    }

    fun getAll(): ArrayList<Series> {
        val readableDatabase = readableDatabase

        val script = "SELECT * FROM SERIES".trimIndent()
        val result = readableDatabase.rawQuery(
            script,
            null
        )
        // logica busqueda
        val exists = result.moveToFirst()

        val allSeries = arrayListOf<Series>()
        if (exists) {
            do {
                val id = result.getInt(0) // Columna indice 0 -> ID
                val name = result.getString(1) // Columna indice 1 -> NAME
                val genre = result.getString(2) // Columna indice 2 -> GENRE
                val isFinished = result.getInt(3) == 1 // Columna indice 3 -> IS_FINISHED
                val seasons = result.getInt(4) // Columna indice 4 -> SEASONS
                val emissionDate = result.getString(5) // Columna indice 5 -> EMISSION_DATE
                val streamingServiceId = result.getInt(6) // Columna indice 6 -> STREAMING_SERVICE_ID

                if (id != null) {
                    val series = Series(
                        id,
                        name,
                        genre,
                        isFinished,
                        seasons,
                        emissionDate,
                        streamingServiceId
                    )
                    allSeries.add(series)
                }
            } while (result.moveToNext())
        }

        return allSeries
    }

    fun getAllByStreamingService(streamingServiceId: Int): ArrayList<Series> {
        val readableDatabase = readableDatabase

        val script = "SELECT * FROM SERIES WHERE STREAMING_SERVICE_ID = ?".trimIndent()
        print(script)
        print(streamingServiceId)
        val result = readableDatabase.rawQuery(
            script,
            arrayOf(streamingServiceId.toString())
        )

        val exists = result.moveToFirst()

        val allSeries = arrayListOf<Series>()

        if (exists) {
            do {
                val id = result.getInt(0) // Columna indice 0 -> ID
                val name = result.getString(1) // Columna indice 1 -> NAME
                val genre = result.getString(2) // Columna indice 2 -> GENRE
                val isFinished = result.getInt(3) == 1 // Columna indice 3 -> IS_FINISHED
                val seasons = result.getInt(4) // Columna indice 4 -> SEASONS
                val emissionDate = result.getString(5) // Columna indice 5 -> EMISSION_DATE
                val streamingServiceId = result.getInt(6) // Columna indice 6 -> STREAMING_SERVICE_ID

                if (id != null) {
                    val series = Series(
                        id,
                        name,
                        genre,
                        isFinished,
                        seasons,
                        emissionDate,
                        streamingServiceId
                    )
                    allSeries.add(series)
                }
            } while (result.moveToNext())
        }

        result.close()
        readableDatabase.close()

        return allSeries
    }

    fun getOne(id: Int): Series {
        val readableDatabase = readableDatabase

        val script = "SELECT * FROM SERIES WHERE ID = ?".trimIndent()

        val result = readableDatabase.rawQuery(
            script,
            arrayOf(id.toString())
        )

        val exists = result.moveToFirst()

        val series = Series()

        if (exists) {
            val id = result.getInt(0) // Columna indice 0 -> ID
            val name = result.getString(1) // Columna indice 1 -> NAME
            val genre = result.getString(2) // Columna indice 2 -> GENRE
            val isFinished = result.getInt(3) == 1 // Columna indice 3 -> IS_FINISHED
            val seasons = result.getInt(4) // Columna indice 4 -> SEASONS
            val emissionDate = result.getString(5) // Columna indice 5 -> EMISSION_DATE
            val streamingServiceId = result.getInt(6) // Columna indice 6 -> STREAMING_SERVICE_ID

            if (id != null) {
                series.setId(id)
                series.setTitle(name)
                series.setGenre(genre)
                series.setIsFinished(isFinished)
                series.setSeasons(seasons)
                series.setEmissionDate(emissionDate)
                series.setStreamingServiceId(streamingServiceId)
            }
        }

        result.close()
        readableDatabase.close()

        return series
    }

    fun create(data: SeriesDto): Boolean {
        val writableDatabase = writableDatabase

        val values = ContentValues()

        values.put("NAME", data.getTitle())
        values.put("GENRE", data.getGenre())
        values.put("IS_FINISHED", data.getIsFinished())
        values.put("SEASONS", data.getSeasons())
        values.put("EMISSION_DATE", data.getEmissionDate())
        values.put("STREAMING_SERVICE_ID", data.getStreamingServiceId())

        val result = writableDatabase.insert(
            "SERIES",
            null,
            values
        )

        writableDatabase.close()

        return result.toInt() != -1
    }

    fun update(id: Int, changes: SeriesDto): Boolean {
        val writableDatabase = writableDatabase

        val values = ContentValues()

        values.put("NAME", changes.getTitle())
        values.put("GENRE", changes.getGenre())
        values.put("IS_FINISHED", changes.getIsFinished())
        values.put("SEASONS", changes.getSeasons())
        values.put("EMISSION_DATE", changes.getEmissionDate())
        values.put("STREAMING_SERVICE_ID", changes.getStreamingServiceId())

        val result = writableDatabase.update(
            "SERIES",
            values,
            "ID = ?",
            arrayOf(id.toString())
        )

        writableDatabase.close()

        return result != -1
    }

    fun remove(id: Int): Boolean {
        val writableDatabase = writableDatabase

        val result = writableDatabase.delete(
            "SERIES",
            "ID = ?",
            arrayOf(id.toString())
        )

        writableDatabase.close()

        return result != -1
    }
}