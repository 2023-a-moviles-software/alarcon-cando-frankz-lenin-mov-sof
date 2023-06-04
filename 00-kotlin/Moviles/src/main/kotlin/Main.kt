import java.util.ArrayList
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

    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null, null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // ARREGLOS

// Tipos de arreglos

// Arreglos estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)

// arreglos dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)

// OPERADORES -> Sirven para los arreglos estaticos y dinamicos

    val respuestaForEach = arregloDinamico
        .forEach { valorActual: Int -> println("Valor actual: ${valorActual}") }
    arregloDinamico.forEach{ println(it) } // it en ingles significa elemento iterado

    arregloEstatico.forEachIndexed { indice, valorActual -> println("Valor: ${valorActual} Indice: ${indice}") }

    println(respuestaForEach)

    // MAP -> Muta el arreglo y crea un nuevo arreglo
    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve es un nuevo arreglo con los valores modificados

    val respuestaMap = arregloDinamico.map { valorActual: Int ->
        println("Dentro del map")
        return@map valorActual.toDouble() + 100.0
    }
    val res = arregloDinamico.map {  }
    val respuestaMap2 = arregloDinamico.map { valorActual: Int -> valorActual.toDouble() + 200.0 } // implicit return

    println(respuestaMap)
    println(respuestaMap2)

    // FILTER -> Filtrar elementos del arreglo
    // 1) Se debe devolver una expresion booleana (TRUE or FALSE)
    // 2) Se devuelve un nuevo arreglo con los datos filtrados

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { value ->
            val mayorACinco = value > 5 //Condicion
            return@filter mayorACinco
        }

    val respuestaFilter2 = arregloDinamico.filter { it <=5 }

    println(respuestaFilter)
    println(respuestaFilter2)

    // OR - AND
    // OR -> ANY (Alguno cumple?)
    // AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico.any { value -> value > 5 }
    println("Alguno es mayor a 5? ${respuestaAny}")

    val respuestaAll: Boolean = arregloDinamico.all { value -> value > 5 }
    println("Todos son mayores a 5? ${respuestaAll}")

    // REDUCE -> Valor agregado
    // Valor acumulado = 0 (Siempre 0 en lenguaje kotlin)
    // [1,2,3,4,5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 2
    // ....

    val respuestaReduce = arregloDinamico
        .reduce { // acc = 0 siempre 0, i = elemento iterado
            acc: Int, i: Int ->  acc + i
        }
    val arr = arrayListOf<String>("Hola ", "como ", "estas", "?")
    val res2 = arr.reduce { acc, palabra -> acc + palabra }
    println(res2)
    println(respuestaReduce)


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


class Suma(// constructor primario suma
    unoParametro: Int, // Parametro
    dosParametro: Int, // Parametro
): Numeros(unoParametro, dosParametro) { // Herencia - Extendiendo y mandando parametros (super)

    init {
        this.numeroUno
        this.numeroDos
    }

    constructor(// segundo constructor
        uno: Int?,
        dos: Int
    ): this(
        if (uno == null) 0 else uno,
        dos
    )

    constructor(
        uno: Int,
        dos: Int?
    ): this(
        uno,
        if (dos == null) 0 else dos
    )

    constructor(
        uno: Int?,
        dos: Int?
    ): this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object { // atributos y metodos "compartidos" Singletons o Static de esta clase
        // Todas las instancias de esta clase comparten estos atributos y metodos dentro
        // companion object

        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int) {
            historialSumas.add(valorNuevaSuma)
        }

    }
}



