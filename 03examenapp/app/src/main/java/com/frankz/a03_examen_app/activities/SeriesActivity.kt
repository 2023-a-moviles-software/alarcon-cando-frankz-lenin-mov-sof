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
import com.frankz.a03_examen_app.models.Series
import com.frankz.a03_examen_app.models.StreamingService

class SeriesActivity : AppCompatActivity() {

    private var series: ArrayList<Series>? = null
    var seletedStreamingServiceId = 0
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        seletedStreamingServiceId = intent.getIntExtra("id", 0)
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
                val streamingService = Database.streamingServices!!.getOne(seletedStreamingServiceId)
                if (streamingService.getId() == seletedStreamingServiceId) {
                    putInt("streamingServiceId", streamingService.getId())
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

    private fun loadSeries(streamingServiceId: Int) {
        if (streamingServiceId != 0) {

            val streamingService = Database.streamingServices!!.getOne(streamingServiceId)

            if (streamingService.getId() == streamingServiceId) {
                series = Database.series!!.getAllByStreamingService(streamingServiceId)

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
                    series!!
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
                val streamingService = Database.streamingServices!!.getOne(seletedStreamingServiceId)

                goToActivity(
                    UpdateSeriesActivity::class.java,
                    Bundle().apply {
                        putInt("streamingServiceId", seletedStreamingServiceId)
                        putString("streamingServiceName", streamingService.getName())
                        putInt("seriesId", selectedSeries.getId())
                        putString("seriesTitle", selectedSeries.getTitle())
                        putString("seriesGenre", selectedSeries.getGenre())
                        putBoolean("seriesIsFinished", selectedSeries.getIsFinished())
                        putInt("seriesSeasons", selectedSeries.getSeasons())
                        putString("seriesEmissionDate", selectedSeries.getEmissionDate().toString())
                    }
                )
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