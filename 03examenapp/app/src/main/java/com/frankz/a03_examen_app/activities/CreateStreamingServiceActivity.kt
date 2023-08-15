package com.frankz.a03_examen_app.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.MainActivity
import com.frankz.a03_examen_app.R
import com.frankz.a03_examen_app.db.Database
import com.frankz.a03_examen_app.dtos.StreamingServiceDto
import com.frankz.a03_examen_app.models.StreamingService

class CreateStreamingServiceActivity : AppCompatActivity() {
    // val streamingServices = HardcodedStreamingServices.streamingServices
    private var streamingServices: ArrayList<StreamingService>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_streaming_service)

        // database
        streamingServices = Database.streamingServices!!.getAll()

        val goBackButton = findViewById<ImageButton>(R.id.btn_go_back)

        goBackButton.setOnClickListener{
            finish()
        }

        val saveNewStreamingServiceButton = findViewById<Button>(
            R.id.btn_new_streaming_service
        )

        saveNewStreamingServiceButton.setOnClickListener {
            createStreamingService()
        }
    }

    fun goBack(mainActivity: Class<MainActivity>) {
        val intent = Intent(this, mainActivity)
        startActivity(intent)
    }

    private fun createStreamingService() {
        val inputName = findViewById<EditText>(R.id.txt_streaming_service_name)
        val inputDescription = findViewById<EditText>(R.id.txt_streaming_service_description)
        val inputPrice = findViewById<EditText>(R.id.txt_streaming_service_price)

        val name = inputName.text.toString()
        val description = inputDescription.text.toString()
        val price = inputPrice.text.toString().toDouble()

        val newStreamingService = StreamingServiceDto(
            name,
            description,
            price
        )

        Database.streamingServices!!.create(newStreamingService)

        finish()
    }
}