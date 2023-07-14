package com.frankz.a03_examen_app.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.R
import com.frankz.a03_examen_app.mocks.HardcodedStreamingServices
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class UpdateSeriesActivity : AppCompatActivity() {
    private val streamingServices = HardcodedStreamingServices.streamingServices
    private val spinnerValues = arrayListOf<String>("Si", "No")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_series)

        loadDataInEditText(intent)

        val goBackButton = findViewById<ImageButton>(
            R.id.ib_go_back_update_series_to_series_list
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val saveUpdatedData = findViewById<Button>(
            R.id.btn_update_series
        )

        saveUpdatedData.setOnClickListener {
            updateSeries()
            finish()
        }
    }

    private fun updateSeries() {
        val inputTitle = findViewById<EditText>(R.id.pt_series_update_title)
        val inputGenre = findViewById<EditText>(R.id.pt_series_update_genre)
        val inputSeasons = findViewById<EditText>(R.id.pt_series_update_seasons)
        val inputEmissionDate = findViewById<EditText>(R.id.pt_series_update_emission_date)
        val spinnerIsSeriesFinished = findViewById<Spinner>(
            R.id.spinner_is_finished_series
        )
        val inputStreamingService = findViewById<EditText>(R.id.pt_streaming_service_title)

        val seriesTitle = inputTitle.text.toString()
        val seriesGenre = inputGenre.text.toString()
        val seriesSeasons = inputSeasons.text.toString().toInt()
        val seriesEmissionDate = inputEmissionDate.text.toString()
        val seriesIsFinished = spinnerIsSeriesFinished.selectedItem.toString() == "Si"
        val streamingServiceName = inputStreamingService.text.toString()

        val streamingServiceId = intent.getStringExtra("streamingServiceId")
        val seriesId = intent.getStringExtra("seriesId")

        val streamingService = streamingServices.find {
            it.getId() == streamingServiceId
        }

        val series = streamingService?.getSeries()?.find {
            it.getId() == seriesId
        }

        series?.setTitle(seriesTitle)
        series?.setGenre(seriesGenre)
        series?.setSeasons(seriesSeasons)
        series?.setEmissionDate(LocalDate.parse(seriesEmissionDate))
        series?.setIsFinished(seriesIsFinished)

        finish()
    }

    private fun loadDataInEditText(intent: Intent) {
        val streamingServiceId = intent.getStringExtra("streamingServiceId")
        val streamingServiceName = intent.getStringExtra("streamingServiceName")
        val seriesId = intent.getStringExtra("seriesId")
        val seriesTitle = intent.getStringExtra("seriesTitle")
        val seriesGenre = intent.getStringExtra("seriesGenre")
        val seriesIsFinished = intent.getBooleanExtra("seriesIsFinished", false)
        val seriesSeasons = intent.getIntExtra("seriesSeasons", 0)
        val seriesEmissionDate = intent.getStringExtra("seriesEmissionDate")

        val inputTitle = findViewById<EditText>(R.id.pt_series_update_title)
        val inputGenre = findViewById<EditText>(R.id.pt_series_update_genre)
        val inputSeasons = findViewById<EditText>(R.id.pt_series_update_seasons)
        val inputEmissionDate = findViewById<EditText>(R.id.pt_series_update_emission_date)
        loadSpinner(seriesIsFinished)
        val spinnerIsSeriesFinished = findViewById<Spinner>(
            R.id.spinner_is_finished_series
        )
        val inputStreamingService = findViewById<EditText>(R.id.pt_streaming_service_title)

        inputTitle.setText(seriesTitle)
        inputGenre.setText(seriesGenre)
        inputSeasons.setText(seriesSeasons.toString())
        inputEmissionDate.setText(seriesEmissionDate)
        inputStreamingService.setText(streamingServiceName)
    }

    private fun loadSpinner(value: Boolean) {
        val spinnerIsSeriesFinished = findViewById<Spinner>(
            R.id.spinner_is_finished_series
        )

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinnerValues
        )

        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerIsSeriesFinished.adapter = arrayAdapter
        val selection = if (value) 0 else 1
        spinnerIsSeriesFinished.setSelection(selection)
    }
}