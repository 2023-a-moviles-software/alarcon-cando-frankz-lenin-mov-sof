package com.frankz.movilessoftware2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result -> if (result.resultCode == RESULT_OK) {
            // LOGICA DE RECIBIR RESPUESTA
            if(result.data != null){
                // Logica Negocio
                val data = result.data
                "${data?.getStringExtra("nombreModificado")}"
            }
        }
    }

    val callbackIntentPickUri = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result -> if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                if (result.data!!.data != null) {
                    val uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri, null, null, null, null, null
                    )

                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )

                    val telefono = cursor?.getString(
                        indiceTelefono!!
                    )

                    cursor?.close()
                    "Telefono: $telefono"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Base de datos
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )

        botonCicloVida.setOnClickListener {
            irActividad(
                AACicloVida::class.java
            )
        }

        val botonListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )

        botonListView.setOnClickListener{
            irActividad(
                BListView::class.java
            )
        }

        val botonIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )

        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackIntentPickUri.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito.setOnClickListener {
            abrirActividadConParametros(
                CIntentExplicitoParametros::class.java
            )
        }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)

        botonSqlite.setOnClickListener {
            irActividad(
                ECrudEntrenador::class.java
            )
        }

        val botonRecyclerView = findViewById<Button>(
            R.id.btn_recycler_view
        )

        botonRecyclerView.setOnClickListener {
            irActividad(
                FRecyclerView::class.java
            )
        }

        val botonGoogleMaps = findViewById<Button>(
            R.id.btn_google_maps
        )

        botonGoogleMaps.setOnClickListener {
            irActividad(
                GGoogleMaps::class.java
            )
        }

        val botonUiAuth = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonUiAuth.setOnClickListener {
            irActividad(
                HFirebaseUIAuth::class.java
            )
        }
    }

    fun abrirActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros
        // (aceptamos primitivos)
        intentExplicito.putExtra("nombre", "Frankz")
        intentExplicito.putExtra("apellido", "Alarcon")
        intentExplicito.putExtra("edad", 21)
        // enviamos el intent con RESPUESTA
        // REIBIMOS RESPUESTA
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)
    }
}