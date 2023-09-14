package com.frankz.a03_examen_app.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.frankz.a03_examen_app.MainActivity
import com.frankz.a03_examen_app.R
import com.frankz.a03_examen_app.db.Database
import com.frankz.a03_examen_app.db.SeriesFirestore
import com.frankz.a03_examen_app.db.StreamingServiceFirestore
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService
import com.google.firebase.firestore.QueryDocumentSnapshot

class SeriesActivity : AppCompatActivity() {

    private var series: ArrayList<Series>? = null
    var seletedStreamingServiceId = ""
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        seletedStreamingServiceId = intent.getStringExtra("id").toString()
        println("streamingServiceId: $seletedStreamingServiceId")

        // load series
        //loadSeries(seletedStreamingServiceId)



        // Buttons and Listeners
        val goBackButton = findViewById<ImageButton>(
            R.id.btn_go_back_to_streaming_services
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val createSeriesButton = findViewById<Button>(
            R.id.btn_create_series
        )

        createSeriesButton.setOnClickListener {
            Database.streamingServices!!.getOne(seletedStreamingServiceId)
                .addOnSuccessListener {
                    val bundle = Bundle()
                    val streamingService = StreamingServiceFirestore.createStreamingServiceFromDocument(it)
                    if (streamingService.getId() == seletedStreamingServiceId) {
                        bundle.putString("streamingServiceId", streamingService.getId())
                        bundle.putString("streamingServiceName", streamingService.getName())
                    }
                    goToActivity(CreateSeriesActivity::class.java, bundle)
                }
        }

    }

    override fun onResume() {
        super.onResume()

        loadSeries(seletedStreamingServiceId)
    }

    private fun goToActivity(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }

    private fun loadSeries(streamingServiceId: String) {
        if (streamingServiceId != "") {

            val streamingService = StreamingService()
            Database.streamingServices!!.getOne(streamingServiceId)
            .addOnSuccessListener {
                val foundStreamingService = StreamingServiceFirestore.createStreamingServiceFromDocument(it)
                streamingService.setId(foundStreamingService.getId())
                streamingService.setName(foundStreamingService.getName())
                streamingService.setDescription(foundStreamingService.getDescription())
                streamingService.setPrice(foundStreamingService.getPrice())

                if (streamingService.getId() == streamingServiceId) {
                    val tvTitle = findViewById<TextView>(
                        R.id.tv_streaming_service
                    )

                    tvTitle.text = streamingService.getName()

                    series = arrayListOf<Series>()
                    Database.series!!.getAllByStreamingService(streamingServiceId)
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            series!!.add(SeriesFirestore.createSeriesFromDocument(document))
                        }

                        val seriesList = findViewById<ListView>(
                            R.id.lv_series
                        )

                        val adapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_list_item_1,
                            series!!
                        )

                        seriesList.adapter = adapter

                        adapter.notifyDataSetChanged()

                        registerForContextMenu(seriesList)
                    }
                    .addOnFailureListener { exception ->
                        println("Error getting documents: $exception")
                    }
                }
            }
        }
    }

    private fun showConfirmDialog(series: Series) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Deseas eliminar la serie: ${series.getTitle()}?")
        builder.setMessage("Una vez eliminado, no lo podrás recuperar")
        builder.setPositiveButton("Sí, eliminar") { dialog, which ->
            Database.series!!.remove(series.getId())
            loadSeries(seletedStreamingServiceId)
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
        inflater.inflate(
            R.menu.menu_series,
            menu
        )

        // position
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        selectedItemId = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedSeries = series!![selectedItemId]
        return when(item.itemId) {
            R.id.mi_edit_series -> {
                Database.streamingServices!!.getOne(seletedStreamingServiceId)
                    .addOnSuccessListener{
                        val streamingService = StreamingServiceFirestore.createStreamingServiceFromDocument(it)
                        goToActivity(
                            UpdateSeriesActivity::class.java,
                            Bundle().apply {
                                putString("streamingServiceId", seletedStreamingServiceId)
                                putString("streamingServiceName", streamingService.getName())
                                putString("seriesId", selectedSeries.getId())
                                putString("seriesTitle", selectedSeries.getTitle())
                                putString("seriesGenre", selectedSeries.getGenre())
                                putBoolean("seriesIsFinished", selectedSeries.getIsFinished())
                                putInt("seriesSeasons", selectedSeries.getSeasons())
                                putString("seriesEmissionDate", selectedSeries.getEmissionDate().toString())
                            }
                        )
                    }
                true
            }
            R.id.mi_delete_series -> {
                showConfirmDialog(selectedSeries)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}