package com.frankz.a03_examen_app.activities

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
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.frankz.a03_examen_app.MainActivity
import com.frankz.a03_examen_app.R
import com.frankz.a03_examen_app.mocks.HardcodedStreamingServices
import com.frankz.a03_examen_app.models.Series

@RequiresApi(Build.VERSION_CODES.O)
class SeriesActivity : AppCompatActivity() {

    val streamingServices = HardcodedStreamingServices.streamingServices
    var seletedStreamingServiceId = ""
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        seletedStreamingServiceId = intent.getStringExtra("id") ?: ""
        println("streamingServiceId: $seletedStreamingServiceId")

        // load series
        loadSeries(seletedStreamingServiceId)



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
            goToActivity(CreateSeriesActivity::class.java, Bundle().apply {
                val streamingService = streamingServices.find {
                    it.getId() == seletedStreamingServiceId
                }
                if (streamingService != null) {
                    putString("streamingServiceId", streamingService.getId())
                    putString("streamingServiceName", streamingService.getName())
                }

            })
        }

    }

    override fun onStart() {
        super.onStart()

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
            val streamingService = streamingServices.find {
                it.getId() == streamingServiceId
            }

            if (streamingService != null) {
                val series = streamingService.getSeries()

                val tvTitle = findViewById<TextView>(
                    R.id.tv_streaming_service
                )

                tvTitle.text = streamingService.getName()

                val seriesList = findViewById<ListView>(
                    R.id.lv_series
                )

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    series
                )

                seriesList.adapter = adapter

                adapter.notifyDataSetChanged()

                registerForContextMenu(seriesList)
            }
        }
    }

    private fun showConfirmDialog(series: Series) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Deseas eliminar la serie: ${series.getTitle()}?")
        builder.setMessage("Una vez eliminado, no lo podrás recuperar")
        builder.setPositiveButton("Sí, eliminar") { dialog, which ->
            val streamingService = streamingServices.find {
                it.getId() == seletedStreamingServiceId
            } ?: return@setPositiveButton

            streamingService.removeSeries(series)
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
        return when(item.itemId) {
            R.id.mi_edit_series -> {
                val streamingService = streamingServices.find {
                    it.getId() == seletedStreamingServiceId
                } ?: return false

                val series = streamingService.getSeries()[selectedItemId]

                goToActivity(
                    UpdateSeriesActivity::class.java,
                    Bundle().apply {
                        putString("streamingServiceId", seletedStreamingServiceId)
                        putString("streamingServiceName", streamingService.getName())
                        putString("seriesId", series.getId())
                        putString("seriesTitle", series.getTitle())
                        putString("seriesGenre", series.getGenre())
                        putBoolean("seriesIsFinished", series.getIsFinished())
                        putInt("seriesSeasons", series.getSeasons())
                        putString("seriesEmissionDate", series.getEmissionDate().toString())
                    }
                )
                true
            }
            R.id.mi_delete_series -> {
                val streamingService = streamingServices.find {
                    it.getId() == seletedStreamingServiceId
                } ?: return false
                showConfirmDialog(streamingService.getSeries()[selectedItemId])
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}