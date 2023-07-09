package com.frankz.movilessoftware2023a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula (
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>(){

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionBoton: Button
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionBoton = view.findViewById(R.id.btn_dar_like)
            accionBoton.setOnClickListener {
                anadirLike()
            }
        }

        fun anadirLike() {
            this.numeroLikes += 1
            likesTextView.text = this.numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }

    // setear el layout que se va a usar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recicler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    // setear los datos de cada elemento, los datos para la iteracion.
    // setear datos iteracion al iniciar el adaptador
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // el holder es la clase interna
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.deescripcion
        holder .accionBoton.text = "Like ${entrenadorActual.id}-${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return this.lista.size
    }

}