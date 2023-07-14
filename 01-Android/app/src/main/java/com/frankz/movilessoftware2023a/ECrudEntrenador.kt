package com.frankz.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        val botonBuscarBdd = findViewById<Button>(R.id.btn_buscar_bdd)

        botonBuscarBdd.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)

            val entrenador = EBaseDeDatos.tablaEntrenador!!
                .consultarEntrenadorPorId(
                    id.text.toString().toInt()
                )
            id.setText(entrenador.id.toString())
            nombre.setText(entrenador.nombre)
            descripcion.setText(entrenador.deescripcion)
        }

        val botonCrearBdd = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBdd.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)

            EBaseDeDatos.tablaEntrenador!!
                .crearEntrenador(
                    nombre.text.toString(),
                    descripcion.text.toString()
                )
        }

        val botonActualizarBdd = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBdd.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)

            EBaseDeDatos.tablaEntrenador!!
                .actualizarEntrenadorFormulario(
                    id = id.text.toString().toInt(),
                    nombre = nombre.text.toString(),
                    descripcion = descripcion.text.toString()
                )
        }

        val botonEliminarBdd = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBdd.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            EBaseDeDatos.tablaEntrenador!!
                .eliminarEntrenadorFormulario(
                    id = id.text.toString().toInt()
                )
        }
    }
}