package com.frankz.a03_examen_app.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import com.frankz.a03_examen_app.R
import com.frankz.a03_examen_app.db.Database
import com.frankz.a03_examen_app.dtos.StreamingServiceDto

class UpdateStreamingServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_streaming_service)

        loadDataInEditText()

        val goBackButton = findViewById<ImageButton>(R.id.btn_go_back)

        goBackButton.setOnClickListener {
            finish()
        }

        val saveUpdateDataButton = findViewById<Button>(R.id.btn_update_streaming_service)

        saveUpdateDataButton.setOnClickListener {
            saveUpdateData()
        }
    }

    private fun loadDataInEditText() {
        val inputName = findViewById<EditText>(R.id.txt_streaming_service_update_name)
        val inputPrice = findViewById<EditText>(R.id.txt_streaming_service_update_price)
        val inputDescription = findViewById<EditText>(R.id.txt_streaming_service_update_description)

        val name = intent.getStringExtra("name")
        val price = intent.getDoubleExtra("price", 0.0)
        val description = intent.getStringExtra("description")

        inputName.setText(name)
        inputPrice.setText(price.toString())
        inputDescription.setText(description)
    }

    private fun saveUpdateData() {
        val inputName = findViewById<EditText>(R.id.txt_streaming_service_update_name)
        val inputPrice = findViewById<EditText>(R.id.txt_streaming_service_update_price)
        val inputDescription = findViewById<EditText>(R.id.txt_streaming_service_update_description)

        val name = inputName.text.toString()
        val price = inputPrice.text.toString()
        val description = inputDescription.text.toString()

        val streamingServiceId = intent.getStringExtra("id")

        val changes = StreamingServiceDto(
            name = name,
            price = price.toDouble(),
            description = description
        )

        if (streamingServiceId != null) {
            Database.streamingServices!!.update(streamingServiceId, changes)
        }

        finish()
    }
}