package com.frankz.movilessoftware2023a

class BBaseDeDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloBEntrenador
                .add(
                    BEntrenador(
                        1,
                        "Frankz",
                        "a@a.com"
                    )
                )
            arregloBEntrenador
                .add(
                    BEntrenador(
                        2,
                        "Vicente",
                        "b@b.com"
                    )
                )
            arregloBEntrenador
                .add(
                    BEntrenador(
                        3,
                        "Pedro",
                        "c@c.com"
                    )
                )
        }
    }
}