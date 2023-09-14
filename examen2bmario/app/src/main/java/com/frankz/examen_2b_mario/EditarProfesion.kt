package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.examenib.negocio.BaseDeDatosMemoria
import com.example.examenib.negocio.Profesion
import com.example.examenib.util.GestionardorIntent
import com.example.examenib.util.Toaster

class EditarProfesion : AppCompatActivity() {
    var profesion: Profesion? = null
    val actividad = GestionardorIntent(this)
    lateinit var mensaje: Toaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_profesion)
        mensaje = Toaster(findViewById(R.id.txt_anadir_profesion))
        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_profesion)


        val id = intent.getIntExtra("profesion", -1)
        Log.i("Profesion","$id")

        profesion = BaseDeDatosMemoria.obtenerProfesionPorId(id)
        profesion?.let {
            cargarDatos(profesion!!)
        }

        botonActualizar.setOnClickListener{
            actualizarProfesion(profesion!!)
        }

    }
    fun cargarDatos(profesion: Profesion){
        val nombre = findViewById<EditText>(R.id.ete_nombre_profesion)
        val descripcion = findViewById<EditText>(R.id.ete_descripcion_profesion)
        val salario = findViewById<EditText>(R.id.ete_salario_promedio)
        val activa = findViewById<CheckBox>(R.id.cbe_activa)
        nombre.setText(profesion.nombre)
        descripcion.setText(profesion.descripcion)
        salario.setText(profesion.salarioPromedio.toString())
        activa.isChecked = profesion.activa
    }

    fun actualizarProfesion(profesion: Profesion){
        val nombre = findViewById<EditText>(R.id.ete_nombre_profesion).text.toString()
        val descripcion = findViewById<EditText>(R.id.ete_descripcion_profesion).text.toString()
        val salario = findViewById<EditText>(R.id.ete_salario_promedio).text.toString().toDouble()
        val activa = findViewById<CheckBox>(R.id.cbe_activa).isChecked

        if(
            nombre.isEmpty() ||
            descripcion.isEmpty() ||
            salario == null
        ){
            mensaje.mostrarMensaje("Debe llenar todos los campos")
            return
        }

        profesion.nombre = nombre
        profesion.descripcion = descripcion
        profesion.salarioPromedio = salario
        profesion.activa = activa

        mensaje.mostrarMensaje("Profesión actualizada")
//        actividad.cambiarActivity(MainActivity::class.java)
        finish()
    }
}