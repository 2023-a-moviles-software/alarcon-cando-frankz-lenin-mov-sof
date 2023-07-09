package com.frankz.a03_examen_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.frankz.a03_examen_app.MainActivity
import com.frankz.a03_examen_app.R

class CreateStreamingServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_streaming_service)

        val goBackButton = findViewById<ImageButton>(R.id.btn_go_back)

        goBackButton.setOnClickListener{
            finish()
        }
    }

    fun goBack(mainActivity: Class<MainActivity>) {
        val intent = Intent(this, mainActivity)
        startActivity(intent)
    }
}