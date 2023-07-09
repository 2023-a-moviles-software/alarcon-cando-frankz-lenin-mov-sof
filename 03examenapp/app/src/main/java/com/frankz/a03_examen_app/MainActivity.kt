package com.frankz.a03_examen_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.frankz.a03_examen_app.activities.CreateStreamingServiceActivity
import com.frankz.a03_examen_app.activities.SeriesActivity
import com.frankz.a03_examen_app.mocks.HardcodedStreamingServices

class MainActivity : AppCompatActivity() {
    val streamingServices = HardcodedStreamingServices.streamingServices
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(
            R.id.lv_streaming_services
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            streamingServices
        )

        listView.adapter = adapter

        adapter.notifyDataSetChanged()

        registerForContextMenu(listView)

        val btnCreate = findViewById<Button>(
            R.id.btn_create_streaming_service
        )

        btnCreate.setOnClickListener {
            goToActivity(CreateStreamingServiceActivity::class.java)
        }
    }


    fun goToActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_streaming_service, menu)

        //position
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        selectedItemId = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_show_series -> {
                "Hacer algo con: ${selectedItemId}"
                goToActivity(SeriesActivity::class.java)
                return true
            }
            R.id.mi_update -> {
                "Hacer algo con: ${selectedItemId}"
                return true
            }
            R.id.mi_delete -> {
                "Hacer algo con: ${selectedItemId}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}