package com.frankz.a03_examen_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.frankz.a03_examen_app.activities.CreateStreamingServiceActivity
import com.frankz.a03_examen_app.activities.SeriesActivity
import com.frankz.a03_examen_app.activities.UpdateStreamingServiceActivity
import com.frankz.a03_examen_app.db.*
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var streamingServices: ArrayList<StreamingService>? = null
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // database
        Database.streamingServices = StreamingServiceFirestore()
        Database.series = SeriesFirestore()

        // load streaming services

        //loadStreamingServices()

        val btnCreate = findViewById<Button>(
            R.id.btn_create_streaming_service
        )

        btnCreate.setOnClickListener {
            goToActivity(CreateStreamingServiceActivity::class.java)
        }
    }


    override fun onResume() {
        super.onResume()
        loadStreamingServices()

    }

    private fun createStreamingServiceFromDocument(document: QueryDocumentSnapshot): StreamingService {
        val id = document.id
        val name = document.data["name"] as String?
        val description = document.data["description"] as String?
        val price = document.data["price"] as Double?
        val series = mutableListOf<Series>()

        if (id == null || name == null || description == null || price == null) {
            return StreamingService()
        }

        return StreamingService(id, name, description, price, series)
    }

    private fun loadStreamingServices() {
        val listView = findViewById<ListView>(
            R.id.lv_streaming_services
        )
        streamingServices = arrayListOf<StreamingService>()
        Database.streamingServices!!.getAll()
            .addOnSuccessListener { result ->
                for (document in result) {
                    streamingServices!!.add(createStreamingServiceFromDocument(document))
                    println("${document.id} => ${document.data}")
                }
                if (streamingServices != null) {

                    val adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        streamingServices!!
                    )

                    listView.adapter = adapter

                    adapter.notifyDataSetChanged()

                    registerForContextMenu(listView)
                }
            }
    }


    private fun goToActivity(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }

    private fun showConfirmDeleteDialog(streamingService: StreamingService) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Estás seguro de eliminar: ${streamingService.getName()}?")
        builder.setMessage("Una vez eliminado no se podrá recuperar.")
        builder.setPositiveButton("Sí") { _, _ ->
            val removed = streamingServices!!.removeAt(selectedItemId)
            Database.streamingServices!!.remove(removed.getId())
            loadStreamingServices()
        }
        builder.setNegativeButton("No", null)
        builder.show()
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
        val streamingService = streamingServices!![selectedItemId]
        return when(item.itemId) {
            R.id.mi_show_series -> {
                "Hacer algo con: ${selectedItemId}"
                if (streamingServices == null) return false

                val params = Bundle()
                params.putString("id", streamingService.getId())
                params.putString("name", streamingService.getName())
                params.putDouble("price", streamingService.getPrice())
                params.putString("description", streamingService.getDescription())

                goToActivity(SeriesActivity::class.java, params)
                return true
            }
            R.id.mi_update -> {
                "Hacer algo con: ${selectedItemId}"
                val params = Bundle()
                params.putString("id", streamingService.getId())
                params.putString("name", streamingService.getName())
                params.putDouble("price", streamingService.getPrice())
                params.putString("description", streamingService.getDescription())

                goToActivity(UpdateStreamingServiceActivity::class.java, params)
                return true
            }
            R.id.mi_delete -> {
                showConfirmDeleteDialog(streamingService)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}