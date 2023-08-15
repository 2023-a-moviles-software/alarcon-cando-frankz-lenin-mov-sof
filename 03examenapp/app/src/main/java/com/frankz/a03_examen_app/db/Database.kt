package com.frankz.a03_examen_app.db

class Database {
    companion object {
        var streamingServices: SqliteHelperStreamingService? = null
        var series: SqliteHelperSeries? = null
    }
}