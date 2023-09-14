package com.frankz.a03_examen_app.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.R
import com.frankz.a03_examen_app.db.Database
import com.frankz.a03_examen_app.dtos.SeriesDto
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService
import java.time.LocalDate

class CreateSeriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_series)

        val goBackButton = findViewById<ImageButton>(
            R.id.go_back_to_series_list
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val txtStreamingService = findViewById<EditText>(R.id.pt_series_streaming_service)

        val streamingServiceName = intent.getStringExtra("streamingServiceName").toString()
        txtStreamingService.setText(streamingServiceName)


        val saveNewSeriesButton = findViewById<Button>(
            R.id.btn_new_series
        )

        val streamingServiceId = intent.getStringExtra("streamingServiceId").toString()

        saveNewSeriesButton.setOnClickListener {
            createSeries(streamingServiceId)
        }

    }

    private fun createSeries(streamingServiceId: String) {
        val inputName = findViewById<EditText>(R.id.pt_series_title)
        val inputGenre = findViewById<EditText>(R.id.pt_series_genre)
        val inputSeasons = findViewById<EditText>(R.id.pt_series_seasons)
        val inputEmissionDate = findViewById<EditText>(R.id.pt_series_emission_date)

        val name = inputName.text.toString()
        val genre = inputGenre.text.toString()
        val seasons = inputSeasons.text.toString().toInt()
        val emissionDate = inputEmissionDate.text.toString()

        val newSeries = SeriesDto(
            name,
            genre,
            false,
            seasons,
            emissionDate,
            streamingServiceId
        )

        Database.series!!.create(newSeries)

        finish()
    }
}