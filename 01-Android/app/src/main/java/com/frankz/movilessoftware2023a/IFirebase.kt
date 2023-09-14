package com.frankz.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class IFirebase : AppCompatActivity() {
    var query: Query? = null
    val arreglo: ArrayList<ICities> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirebase)

        // Configuracion de list view
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador

        adaptador.notifyDataSetChanged()

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener { consultarConOrderBy(adaptador) }

        val botonObtenerDocumento =findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener { consultarDocumento(adaptador) }

        val botonIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonIndiceCompuesto.setOnClickListener { consultarIndiceCompuesto(adaptador) }

        // Datos de Prueba
        val botonCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonCrear.setOnClickListener { crearEjemplo() }

        val botonEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonEliminar.setOnClickListener { eliminar() }

        val botonEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonEmpezarPaginar.setOnClickListener { consultarCiudades(adaptador) }
    }

    fun consultarCiudades(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore

        val citiesRef = db.collection("cities")
            .orderBy("population")
            .limit(1)

        var tarea: Task<QuerySnapshot>? = null

        if (query == null) {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            tarea = citiesRef.get()
        } else {
            // Empieza desde el ultimo documento, esto se hace en guardarQuery
            tarea = query!!.get()
        }

        if (tarea != null) {
            tarea.addOnSuccessListener {
                documentSnapshots ->
                guardarQuery(documentSnapshots, citiesRef)
                for (ciudad in documentSnapshots) {
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
                .addOnFailureListener {  }
        }
    }

    fun guardarQuery(documentSnapshots: QuerySnapshot, refCities: Query) {
        if (documentSnapshots.size() > 0) {
            val ultimoDocumento = documentSnapshots.documents[documentSnapshots.size() - 1]
            // Establece el query para que empiece con el ultimo documento para la siguiente paginacion
            query = refCities
                .startAfter(ultimoDocumento)
        } else {
            query = null
        }
    }

    fun eliminar() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("ejemplo")

        referenciaEjemploEstudiante
            .document("12345678")
            .delete()
            .addOnSuccessListener {
                // Success
            }
            .addOnFailureListener {
                // Error
            }
    }

    fun crearEjemplo() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("ejemplo")

        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Frankz",
            "graduado" to false,
            "promedio" to 15.00,
            "direccion" to hashMapOf(
                "calle" to "Queseras del Medio",
                "numeroCalle" to 123,
            ),
            "materias" to listOf("moviles", "hci")
        )

        // Identificador quemado (crear/actualizar)
        referenciaEjemploEstudiante
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener {
                // Success
            }
            .addOnFailureListener {
                // Error
            }

        // Identificador quemado pero autogenerado con Date().time
        referenciaEjemploEstudiante // crear/actualizar
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener {
                // Success
            }
            .addOnFailureListener {
                // Error
            }

        // Identificador autogenerado por Firestore
        referenciaEjemploEstudiante // crear
            .add(datosEstudiante)
            .addOnSuccessListener {
                // Success
            }
            .addOnFailureListener {
                // Error
            }



    }


    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore

        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {

            }
    }

    fun consultarDocumento(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore

        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        // Coleccion "ciudad"
        //      -> Coleccion "barrio"
        //          -> Coleccion "direccion"
        // "Quito" => La_Floresta => "E90-001"

        // db.collection("ciudad").document("La_Floresta).collection("direccion")
        //  .document("E90-001")

        // .colletion("nombre_coleccion_hijo").document("id_hijo)
        // .collection("nombre_coleccion_nieto").document("id_nieto)

        citiesRefUnico
            .document("BJ")
            .get()
            .addOnSuccessListener {
                // it -> Es un objeto
                arreglo.add(
                    ICities(
                        it.data?.get("name") as String?,
                        it.data?.get("state") as String?,
                        it.data?.get("country") as String?,
                        it.data?.get("capital") as Boolean?,
                        it.data?.get("population") as Number?,
                        it.data?.get("regions") as ArrayList<String>?,
                    )
                )
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                //Errores
            }
    }

    fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }

    fun consultarConOrderBy(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore

        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()

        adaptador.notifyDataSetChanged()

        citiesRefUnico
            .orderBy("population", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { // it -> it es un QuerySnapshot (lo que llegue)
                for (ciudad in it) {
                    ciudad.id
                    anadirAArregloCiudad(
                        ciudad,
                    )
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{
                // Errores
            }
    }

    fun limpiarArreglo() { arreglo.clear() }

    fun anadirAArregloCiudad(
        ciudad: QueryDocumentSnapshot,
    ) {
        val nuevaCiudad = ICities(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Number?,
            ciudad.data.get("regions") as ArrayList<String>?,
        )
        arreglo.add(nuevaCiudad)
    }
}