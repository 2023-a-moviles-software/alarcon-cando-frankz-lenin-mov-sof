package com.frankz.movilessoftware2023a

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class GGoogleMaps : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)

        solicitarPermisos()
        iniciarLogicaMapa()

        val botonIrCarolina = findViewById<Button>(R.id.btn_ir_carolina)
        botonIrCarolina.setOnClickListener {
            irCarolina()
        }
    }

    fun irCarolina() {
        val carolina = LatLng(-0.18255808, -78.484472011)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }

    fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        fragmentoMapa.getMapAsync {
            googleMap -> with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa()
                marcadorQuicentro()
                anadirPolilinea()
                anadirPoligono()
                escucharListeners()
        }
        }
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener $it")
            it.tag // ID
        }

        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener $it")
            it.tag // ID
        }

        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener $it")
            it.tag // ID
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener $it")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }


    fun anadirPolilinea() {
        with(mapa) {
            val poliLineaUno = mapa
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.17591808, -78.485149011),
                            LatLng(-0.17632408, -78.482149011),
                            LatLng(-0.17776708, -78.477149011),
                        )
                )
            poliLineaUno.tag = "linea-1"
        }
    }

    fun anadirPoligono() {
        with(mapa) {
            val poligonoUno = mapa
                .addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.17715708, -78.483149011),
                            LatLng(-0.17966708, -78.482149011),
                            LatLng(-0.17776708, -78.481149011),
                        )
                )

            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag = "Poligono-2" // ID
        }
    }

    fun marcadorQuicentro() {
        val zoom = 17f
        val quicentro = LatLng(-0.17556708, -78.480149011)
        val titulo = "Quicentro"
        val markQuicentro = anadirMarcador(quicentro, titulo)
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro, zoom)
    }

    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with (mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    // permiso que van a checkear
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }



    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION

        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
            contexto,
            // permiso que van a checkear
            nombrePermisoFine
        )

        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    nombrePermisoFine,
                    nombrePermisoCoarse
                ),
                1 // Codigo que vamos a esperar
            )
        }
    }

}