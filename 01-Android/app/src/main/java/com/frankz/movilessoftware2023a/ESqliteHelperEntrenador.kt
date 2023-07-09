package com.frankz.movilessoftware2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (
    contexto: Context?, // this
    ): SQLiteOpenHelper(
    contexto,
    "moviles", // nombre de la base de datos
    null,
    1
    ) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Code
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    NOMBRE VARCHAR(50),
                    DESCRIPCION VARCHAR(50)
                )
            """.trimIndent()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Code
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = "SELECT * FROM ENTRENADOR WHERE ID = ?".trimIndent()
        val resultadosConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            arrayOf(id.toString())
        )
        // logica busqueda
        val existeEntrenador = resultadosConsultaLectura.moveToFirst()

        val entrenadorEncontrado = BEntrenador(0, "", "")

        val arregloEntrenadores = arrayListOf<BEntrenador>()
        if (existeEntrenador) {
            do {
                val id = resultadosConsultaLectura.getInt(0) // Columna indice 0 -> ID
                val nombre = resultadosConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
                val descripcion = resultadosConsultaLectura.getString(2) // Columna indice 2 -> DESCRIPCION

                if (id != null) {
                    entrenadorEncontrado.id = id
                    entrenadorEncontrado.nombre = nombre
                    entrenadorEncontrado.deescripcion = descripcion
                }
            } while (resultadosConsultaLectura.moveToNext())
        }

        resultadosConsultaLectura.close()
        baseDatosLectura.close()

        return entrenadorEncontrado
    }

    fun crearEntrenador(nombre: String, descripcion: String): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoGuardar = baseDatosEscritura
            .insert(
                "ENTRENADOR",
                null,
                valoresAGuardar
            )

        baseDatosEscritura.close()

        return resultadoGuardar.toInt() != -1
    }

    fun actualizarEntrenadorFormulario(id: Int, nombre: String, descripcion: String): Boolean {
        val conexionEscritura = writableDatabase

        val valoresAActualizar = ContentValues()

        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR", // Nombre Tabla
                valoresAActualizar, // Valores a actualizar
                "ID=?", // Clausula Where
                parametrosConsultaActualizar // Parametros consulta
            )

        conexionEscritura.close()

        return resultadoActualizacion.toInt() != -1
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase

        // DELETE FROM ENTRENADOR WHERE ID=id
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "ID=?", // Clausula WHERE
                parametrosConsultaDelete
            )

        conexionEscritura.close()

        return resultadoEliminacion.toInt() != -1
    }
}