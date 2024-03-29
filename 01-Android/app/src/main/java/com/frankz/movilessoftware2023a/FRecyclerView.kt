package com.frankz.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FRecyclerView : AppCompatActivity() {

    var totalLikes = 0
    val arreglo = BBaseDeDatosMemoria.arregloBEntrenador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecycler_view)

        // inicializar reccycler view
        inicializarRecyclerView()
    }

    fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_entrenadores
        )

        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            this,
            arreglo,
            recyclerView
        )

        recyclerView.adapter = adaptador

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.layoutManager = LinearLayoutManager(this)

        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes() {
        totalLikes = totalLikes + 1
        val totalLikesTextView = findViewById<TextView>(
            R.id.tv_total_likes
        )
        totalLikesTextView.text = totalLikes.toString()
    }
}