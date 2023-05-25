import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    // Inmutable -> se empieza con "val", no se reasignan
    val inmutable: String = "Frankz";
    // inmutable = "Lenin" -> Error

    // Mutable -> Se puede reasignar
    var mutable = "Alarcon"
    mutable = "Cando"

    println("${inmutable} ${mutable}")

    // val > var -> siempre preferir variables inmutables sobre mutables

    // Duck typing -> Inferir variables
    var ejemploVariable = "Frankz Alarcon"
    val edadEjemplo: Int = 12

    ejemploVariable.trim()

    // variables primitivas
    val nombreProfesor = "Frankz Alarcon"
    val sueldo = 1200;
    val estadoCivil = "Soltero"
    val esMayorEdad = true

    //Clases Java
    val fechaNacimiento = Date()

    // Switch
    val estadoCivilWhen = "C"

    when (estadoCivilWhen) {
        "C" -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    // If
    val esSoltero = estadoCivilWhen == "S"
    val coqueteo = if (esSoltero) "Si" else "No"

    // Llamado a funciones
    calcularSueldo(10.0)
    calcularSueldo(10.0, 12.00, 20.00)
    calcularSueldo(10.0, bonoEspecial = 40.0) // named parameters
    calcularSueldo(bonoEspecial = 50.0, sueldo = 1200.0, tasa = 20.0) // named parameters
}


// Funciones
// Por defecto, si no retorna nada, retorna Unit
fun imprimirNombre(nombre: String) {
    println("Nombre: ${nombre}") // template String
}

fun calcularSueldo(
    sueldo: Double, // requerido
    tasa: Double = 12.00, // opcional (tiene un valor por defecto)
    bonoEspecial: Double? = null // opcion null -> nullable
): Double {
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)

    if (bonoEspecial == null) {
        return  sueldo * (100 / tasa)
    }

    return sueldo * (100 / tasa) + bonoEspecial
}


// Clases
abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {
        this.numeroUno = uno
        this.numeroDos = dos

        print("Inicializando")
    }
}

abstract class Numeros (// Constructor Primario
//    Ejemplo>
    // uno: Int, (Parametro (sin modificador de acceso))
    // private var uno: Int (propiedad privada clase numeros.uno)
    // var uno: Int (propiedad de la clase (por defecto es PUBLIC))
    // public var uno: Int

    protected val numeroUno: Int, // propiedad protected de la clase Numeros, numeros.numeroUno
    protected  val numeroDos: Int // propiedad protected de la clase Numeros, numeros.numeroDos
) {
    init { // Bloque de codigo primario
        this.numeroUno; this.numeroDos

        numeroUno; numeroDos

        println("Inicializando")
    }
}