package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.examenib.negocio.BaseDeDatosMemoria
import com.example.examenib.negocio.Profesion
import com.example.examenib.util.GestionardorIntent
import com.example.examenib.util.Toaster

class AnadirProfesion : AppCompatActivity() {
    var profesion: Profesion? = null
    val actividad = GestionardorIntent(this)
    lateinit var mensaje: Toaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_profesion)
        mensaje = Toaster(findViewById(R.id.txt_anadir_profesion))
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_profesion)
        botonGuardar.setOnClickListener{
            guardarProfesion()
        }
    }
    fun guardarProfesion(){
        val nombre = findViewById<EditText>(R.id.et_nombre_profesion).text.toString()
        val descripcion = findViewById<EditText>(R.id.et_descripcion_profesion).text.toString()
        val salario = findViewById<EditText>(R.id.et_salario_promedio).text.toString()
        val activa = findViewById<CheckBox>(R.id.cb_activa).isChecked

        if(
            nombre.isEmpty() ||
            descripcion.isEmpty() ||
            salario.isEmpty()
        ){
            mensaje.mostrarMensaje("Debe llenar todos los campos")
            return
        }

        profesion = Profesion(
            BaseDeDatosMemoria.arregloProfesion.size + 1,
            nombre,
            descripcion,
            activa,
            salario.toDouble()
        )
        BaseDeDatosMemoria.arregloProfesion.add(profesion!!)
        mensaje.mostrarMensaje("Profesión guardada")
        finish()
    }
}