package com.frankz.pills_reminder.models

class Medicament(
    val id: String,
    val name: String,
    val hour: String,
    val frequency: String,
    val dose: String
) {
    override fun toString(): String {
        return "· $name, Hora: $hour - Dósis: $dose"
    }
}