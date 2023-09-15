package com.frankz.pills_reminder.models

class Treatment(
    val id: String,
    val disease: String,
    val description: String,
    val initialDate: String,
    val finalDate: String,
    val medicaments: ArrayList<Medicament>
) {
}